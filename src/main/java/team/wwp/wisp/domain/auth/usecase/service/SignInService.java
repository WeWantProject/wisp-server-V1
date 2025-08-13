package team.wwp.wisp.domain.auth.usecase.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.auth.exception.PasswordInvalidException;
import team.wwp.wisp.domain.auth.presentation.data.response.AuthTokenResponse;
import team.wwp.wisp.domain.auth.usecase.SignInUseCase;
import team.wwp.wisp.domain.user.domain.User;
import team.wwp.wisp.domain.user.port.UserPersistencePort;
import team.wwp.wisp.global.security.jwt.data.TokenDto;
import team.wwp.wisp.global.security.jwt.service.usecase.JwtUseCase;

@Service
@RequiredArgsConstructor
public class SignInService implements SignInUseCase {

    private final JwtUseCase jwtUseCase;
    private final UserPersistencePort userPersistencePort;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthTokenResponse execute(String phoneNumber, String password) {
        User user = userPersistencePort.findUserByPhoneNumber(phoneNumber);
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            TokenDto accessToken = jwtUseCase.issueAccessToken(user.getPhoneNumber(), user.getRole());
            TokenDto refreshToken = jwtUseCase.issueRefreshToken(user.getPhoneNumber());
            return new AuthTokenResponse(
                accessToken.token(),
                refreshToken.token(),
                accessToken.expiration(),
                refreshToken.expiration(),
                user.getRole()
            );
        } else {
            throw new PasswordInvalidException();
        }
    }
}
