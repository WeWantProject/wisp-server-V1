package team.wwp.wisp.domain.auth.presentation.data.response;

import java.time.LocalDateTime;

import team.wwp.wisp.domain.user.domain.constant.UserRole;

public record AuthTokenResponse(
    String accessToken,
    String refreshToken,
    LocalDateTime accessTokenExpiration,
    LocalDateTime refreshTokenExpiration,
    UserRole role
) {   
}
