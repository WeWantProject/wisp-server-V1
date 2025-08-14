package team.wwp.wisp.global.security.jwt.service;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.user.domain.constant.UserRole;
import team.wwp.wisp.global.security.jwt.data.JwtEnvironment;
import team.wwp.wisp.global.security.jwt.repository.RefreshTokenRedisRepository;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtParserUseCase;

@Service
@RequiredArgsConstructor
public class JwtParserService implements JwtParserUseCase {
    
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
    public Boolean validateAccessToken(String accessToken) {
        try {
            parseAccessTokenClaims(accessToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean validateRefreshToken(String refreshToken) {
        try {
            parseRefreshTokenClaims(refreshToken);
            return refreshTokenRedisRepository.existsById(refreshToken);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getPhoneNumberFromAccessToken(String accessToken) {
        return parseAccessTokenClaims(accessToken).getSubject();
    }

    @Override
    public String getPhoneNumberFromRefreshToken(String refreshToken) {
        return parseRefreshTokenClaims(refreshToken).getSubject();
    }

    @Override
    public UserRole getRoleFromAccessToken(String accessToken) {
        return UserRole.valueOf(parseAccessTokenClaims(accessToken).get("role", String.class));
    }

    @Override
    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        return (token != null && token.startsWith("Bearer ")) ? token.substring(7) : null;
    }

    private Claims parseAccessTokenClaims(String accessToken) {
        return Jwts.parser()
            .verifyWith(accessTokenKey)
            .build()
            .parseSignedClaims(accessToken)
            .getPayload();
    }

    private Claims parseRefreshTokenClaims(String refreshToken) {
        return Jwts.parser()
            .verifyWith(refreshTokenKey)
            .build()
            .parseSignedClaims(refreshToken)
            .getPayload();
    }
}
