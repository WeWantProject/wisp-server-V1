package team.wwp.wisp.domain.auth.usecase.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.auth.exception.RefreshTokenInvalidException;
import team.wwp.wisp.domain.auth.presentation.data.response.AuthTokenResponse;
import team.wwp.wisp.domain.auth.usecase.RefreshUseCase;
import team.wwp.wisp.domain.user.domain.User;
import team.wwp.wisp.domain.user.port.UserPersistencePort;
import team.wwp.wisp.global.security.jwt.data.TokenDto;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtUseCase;

@Service
@RequiredArgsConstructor
public class RefreshService implements RefreshUseCase {
    
    private final JwtUseCase jwtUseCase;
    private final UserPersistencePort userPersistencePort;

    public AuthTokenResponse execute(String refreshToken) {
        if (jwtUseCase.validateRefreshToken(refreshToken)) {
            String phoneNumber = jwtUseCase.getPhoneNumberFromRefreshToken(refreshToken);
            User user = userPersistencePort.findUserByPhoneNumber(phoneNumber);
            TokenDto newAccessToken = jwtUseCase.issueAccessToken(phoneNumber, user.getRole());
            TokenDto newRefreshToken = jwtUseCase.issueRefreshToken(phoneNumber);
            jwtUseCase.deleteRefreshToken(refreshToken);
            return new AuthTokenResponse(
                newAccessToken.token(),
                newRefreshToken.token(),
                newAccessToken.expiration(),
                newRefreshToken.expiration(),
                user.getRole()
            );
        } else {
            throw new RefreshTokenInvalidException();
        }
    }
}
