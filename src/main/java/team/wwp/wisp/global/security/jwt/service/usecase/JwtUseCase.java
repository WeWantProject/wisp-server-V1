package team.wwp.wisp.global.security.jwt.service.usecase;

import jakarta.servlet.http.HttpServletRequest;
import team.wwp.wisp.domain.user.domain.constant.UserRole;
import team.wwp.wisp.global.security.jwt.data.TokenDto;

public interface JwtUseCase {
    TokenDto issueAccessToken(String phoneNumber, UserRole role);

    TokenDto issueRefreshToken(String phoneNumber);

    Boolean validateAccessToken(String token);

    Boolean validateRefreshToken(String token);

    String getPhoneNumberFromAccessToken(String token);

    String getPhoneNumberFromRefreshToken(String token);

    UserRole getRoleFromAccessToken(String token);

    String resolveToken(HttpServletRequest request);

    void deleteRefreshToken(String refreshToken);
}
