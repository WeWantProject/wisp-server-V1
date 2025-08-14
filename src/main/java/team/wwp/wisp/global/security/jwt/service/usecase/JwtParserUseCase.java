package team.wwp.wisp.global.security.jwt.service.usecase;

import jakarta.servlet.http.HttpServletRequest;
import team.wwp.wisp.domain.user.domain.constant.UserRole;

public interface JwtParserUseCase {
    Boolean validateAccessToken(String accessToken);

    Boolean validateRefreshToken(String refreshToken);

    String getPhoneNumberFromAccessToken(String accessToken);

    String getPhoneNumberFromRefreshToken(String refreshToken);

    UserRole getRoleFromAccessToken(String accessToken);

    String resolveToken(HttpServletRequest request);
}
