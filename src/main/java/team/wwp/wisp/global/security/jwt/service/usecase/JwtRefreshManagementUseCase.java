package team.wwp.wisp.global.security.jwt.service.usecase;

public interface JwtRefreshManagementUseCase {
    void deleteRefreshToken(String refreshToken);
}
