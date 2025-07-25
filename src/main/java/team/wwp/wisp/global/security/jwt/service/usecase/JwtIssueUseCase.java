package team.wwp.wisp.global.security.jwt.service.usecase;

import team.wwp.wisp.domain.user.domain.constant.UserRole;
import team.wwp.wisp.global.security.jwt.data.TokenDto;

public interface JwtIssueUseCase {
    TokenDto issueAccessToken(String email, UserRole role);

    TokenDto issueRefreshToken(String email);
}
