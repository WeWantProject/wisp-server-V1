package team.wwp.wisp.global.security.jwt.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.user.domain.constant.UserRole;
import team.wwp.wisp.global.security.jwt.data.JwtEnvironment;
import team.wwp.wisp.global.security.jwt.data.TokenDto;
import team.wwp.wisp.global.security.jwt.entity.RefreshTokenRedisEntity;
import team.wwp.wisp.global.security.jwt.repository.RefreshTokenRedisRepository;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtIssueUseCase;

@Service
@RequiredArgsConstructor
public class JwtIssueService implements JwtIssueUseCase {
    
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final JwtEnvironment jwtEnvironment;

    private SecretKey accessTokenKey;
    private SecretKey refreshTokenKey;

    @PostConstruct
    public void init() {
        accessTokenKey = Keys.hmacShaKeyFor(jwtEnvironment.accessToken().secret().getBytes());
        refreshTokenKey = Keys.hmacShaKeyFor(jwtEnvironment.refreshToken().secret().getBytes());
    }

    @Override
    public TokenDto issueAccessToken(String phoneNumber, UserRole role) {
        LocalDateTime expiration = LocalDateTime.now().plusSeconds(jwtEnvironment.accessToken().expiration());
        return new TokenDto(
            Jwts.builder()
                .claim("sub", phoneNumber)
                .claim("role", role)
                .claim("iat", LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toEpochSecond())
                .claim("exp", expiration.atZone(ZoneId.of("Asia/Seoul")).toEpochSecond())
                .signWith(accessTokenKey)
                .compact(),
            expiration
        );
    }

    @Override
    public TokenDto issueRefreshToken(String phoneNumber) {
        LocalDateTime expiration = LocalDateTime.now().plusSeconds(jwtEnvironment.refreshToken().expiration());
        TokenDto token = new TokenDto(
            Jwts.builder()
                .claim("sub", phoneNumber)
                .claim("iat", LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toEpochSecond())
                .claim("exp", expiration.atZone(ZoneId.of("Asia/Seoul")).toEpochSecond())
                .signWith(refreshTokenKey)
                .compact(),
            expiration
        );

        refreshTokenRedisRepository.save(RefreshTokenRedisEntity.builder()
            .token(token.token())
            .phoneNumber(phoneNumber)
            .expiration((long)expiration.getSecond())
            .build()
        );
        return token;
    }
}
