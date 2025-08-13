package team.wwp.wisp.global.security.jwt.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.user.domain.constant.UserRole;
import team.wwp.wisp.global.security.jwt.data.TokenDto;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtIssueUseCase;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtParserUseCase;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtRefreshManagementUseCase;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtUseCase;

@Service
@RequiredArgsConstructor
public class JwtService implements JwtUseCase {
    
    private final JwtIssueUseCase jwtIssueUseCase;
    private final JwtParserUseCase jwtParserUseCase;
    private final JwtRefreshManagementUseCase jwtRefreshManagementUseCase;

    public TokenDto issueAccessToken(String phoneNumber, UserRole role) {
        return jwtIssueUseCase.issueAccessToken(phoneNumber, role);
    }

    public TokenDto issueRefreshToken(String phoneNumber) {
        return jwtIssueUseCase.issueRefreshToken(phoneNumber);
    }

    public Boolean validateAccessToken(String token) {
        return jwtParserUseCase.validateAccessToken(token);
    }

    public Boolean validateRefreshToken(String token) {
        return jwtParserUseCase.validateRefreshToken(token);
    }

    public String getPhoneNumberFromAccessToken(String token) {
        return jwtParserUseCase.getPhoneNumberFromAccessToken(token);
    }

    public String getPhoneNumberFromRefreshToken(String token) {
        return jwtParserUseCase.getPhoneNumberFromRefreshToken(token);
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
