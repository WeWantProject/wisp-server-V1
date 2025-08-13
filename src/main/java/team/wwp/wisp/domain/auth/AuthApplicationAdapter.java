package team.wwp.wisp.domain.auth;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.auth.port.AuthApplicationPort;
import team.wwp.wisp.domain.auth.presentation.data.response.AuthTokenResponse;
import team.wwp.wisp.domain.auth.usecase.ChangePasswordUseCase;
import team.wwp.wisp.domain.auth.usecase.RefreshUseCase;
import team.wwp.wisp.domain.auth.usecase.SendAuthenticationSmsUseCase;
import team.wwp.wisp.domain.auth.usecase.SignInUseCase;
import team.wwp.wisp.domain.auth.usecase.SignUpUseCase;
import team.wwp.wisp.domain.auth.usecase.VerifyPhoneUseCase;

@Service
@RequiredArgsConstructor
public class AuthApplicationAdapter implements AuthApplicationPort {
    
    private final SignInUseCase signInUseCase;
    private final SignUpUseCase signUpUseCase;
    private final VerifyPhoneUseCase verifyPhoneUseCase;
    private final SendAuthenticationSmsUseCase sendAuthenticationSmsUseCase;
    private final RefreshUseCase refreshUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    @Override
    public void signUp(String username, String phoneNumber, String password, String displayName) {
        signUpUseCase.execute(username, phoneNumber, password, displayName);
    }

    @Override
    public AuthTokenResponse signIn(String phoneNumber, String password) {
        return signInUseCase.execute(phoneNumber, password);
    }

    @Override
    public AuthTokenResponse refresh(String refreshToken) {
        return refreshUseCase.execute(refreshToken);
    }

    @Override
    public void verifyPhone(String code) {
        verifyPhoneUseCase.execute(code);
    }

    @Override
    public void sendAuthenticationSms(String phoneNumber) {
        sendAuthenticationSmsUseCase.execute(phoneNumber);
    }

    @Override
    public void changePassword(String phoneNumber, String newPassword) {
        changePasswordUseCase.execute(phoneNumber, newPassword);
    }
}
