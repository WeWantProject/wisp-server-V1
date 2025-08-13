package team.wwp.wisp.domain.auth.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.auth.port.AuthApplicationPort;
import team.wwp.wisp.domain.auth.presentation.data.request.ChangePasswordRequest;
import team.wwp.wisp.domain.auth.presentation.data.request.RefreshRequest;
import team.wwp.wisp.domain.auth.presentation.data.request.SendSmsRequest;
import team.wwp.wisp.domain.auth.presentation.data.request.SignInRequest;
import team.wwp.wisp.domain.auth.presentation.data.request.SignUpRequest;
import team.wwp.wisp.domain.auth.presentation.data.request.VerifyCodeRequest;
import team.wwp.wisp.domain.auth.presentation.data.response.AuthTokenResponse;

@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
public class AuthWebAdapter {
    
    private final AuthApplicationPort authApplicationPort;

    @PostMapping("/signin")
    public ResponseEntity<AuthTokenResponse> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authApplicationPort.signIn(signInRequest.phoneNumber(), signInRequest.password()));
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        authApplicationPort.signUp(signUpRequest.username(), signUpRequest.phoneNumber(), signUpRequest.password(), signUpRequest.displayName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/refresh")
    public ResponseEntity<AuthTokenResponse> refresh(@Valid @RequestBody RefreshRequest refreshRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authApplicationPort.refresh(refreshRequest.refreshToken()));
    }

    @PatchMapping("/verify-phone")
    public ResponseEntity<Void> verifyPhone(@Valid @RequestBody VerifyCodeRequest verifyCodeRequest) {
        authApplicationPort.verifyPhone(verifyCodeRequest.code());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/send-sms")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> sendAuthenticationSms(@Valid @RequestBody SendSmsRequest sendSmsRequest) {
        authApplicationPort.sendAuthenticationSms(sendSmsRequest.phoneNumber());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/change-password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        authApplicationPort.changePassword(changePasswordRequest.phoneNumber(), changePasswordRequest.newPassword());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
