package team.wwp.wisp.domain.auth.port;

import team.wwp.wisp.domain.auth.presentation.data.response.AuthTokenResponse;

public interface AuthApplicationPort {
    void signUp(String username, String phoneNumber, String password, String displayName);
    AuthTokenResponse signIn(String phoneNumber, String password);
    AuthTokenResponse refresh(String refreshToken);
    void verifyPhone(String code);
    void sendAuthenticationSms(String phoneNumber);
    void changePassword(String phoneNumber, String newPassword);
}
