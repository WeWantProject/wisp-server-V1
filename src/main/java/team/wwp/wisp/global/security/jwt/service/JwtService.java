package team.wwp.wisp.global.security.jwt.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.user.domain.constant.UserRole;
import team.wwp.wisp.global.security.jwt.data.TokenDto;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtIssueUseCase;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtParserUseCase;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtRefreshManagementUseCase;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtUseCase;

@RequiredArgsConstructor
public class JwtService implements JwtUseCase {
    
    private final JwtIssueUseCase jwtIssueUseCase;
    private final JwtParserUseCase jwtParserUseCase;
    private final JwtRefreshManagementUseCase jwtRefreshManagementUseCase;

    public TokenDto issueAccessToken(String email, UserRole role) {
        return jwtIssueUseCase.issueAccessToken(email, role);
    }

    public TokenDto issueRefreshToken(String email) {
        return jwtIssueUseCase.issueRefreshToken(email);
    }

    public Boolean validateAccessToken(String token) {
        return jwtParserUseCase.validateAccessToken(token);
    }

    public Boolean validateRefreshToken(String token) {
        return jwtParserUseCase.validateRefreshToken(token);
    }

    public String getEmailFromAccessToken(String token) {
        return jwtParserUseCase.getEmailFromAccessToken(token);
    }

    public String getEmailFromRefreshToken(String token) {
        return jwtParserUseCase.getEmailFromRefreshToken(token);
    }
    
    public UserRole getRoleFromAccessToken(String token) {
        return jwtParserUseCase.getRoleFromAccessToken(token);
    }

    public String resolveToken(HttpServletRequest request) {
        return jwtParserUseCase.resolveToken(request);
    }
    
    public void deleteRefreshToken(String refreshToken) {
        jwtRefreshManagementUseCase.deleteRefreshToken(refreshToken);
    }
}
