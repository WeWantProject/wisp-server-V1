package team.wwp.wisp.global.security.jwt.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.wwp.wisp.global.security.jwt.repository.RefreshTokenRedisRepository;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtRefreshManagementUseCase;

@Service
@RequiredArgsConstructor
public class JwtRefreshManagementService implements JwtRefreshManagementUseCase {
    
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Override
    public void deleteRefreshToken(String refreshToken) {
        refreshTokenRedisRepository.deleteById(refreshToken);
    }
}
