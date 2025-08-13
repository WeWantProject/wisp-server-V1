package team.wwp.wisp.domain.auth.usecase.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.auth.domain.AuthCode;
import team.wwp.wisp.domain.auth.domain.Authentication;
import team.wwp.wisp.domain.auth.port.AuthCodePersistencePort;
import team.wwp.wisp.domain.auth.port.AuthenticationPersistencePort;
import team.wwp.wisp.domain.auth.usecase.VerifyPhoneUseCase;
import team.wwp.wisp.global.thirdparty.sms.exception.VerificationInvalidException;

@Service
@RequiredArgsConstructor
public class VerifyPhoneService implements VerifyPhoneUseCase {
    
    private final AuthCodePersistencePort authCodePersistencePort;
    private final AuthenticationPersistencePort authenticationPersistencePort;

    public void execute(String code) {
        if (!authCodePersistencePort.existsAuthCodeByCode(code)) {
            throw new VerificationInvalidException();
        }

        AuthCode authCode = authCodePersistencePort.findAuthCodeByCode(code);
        authCodePersistencePort.deleteAuthCodeByCode(code);
        Authentication authentication = authenticationPersistencePort.findAuthenticationByPhoneNumber(authCode.getPhoneNumber());
        Authentication updatedAuthentication = Authentication.builder()
            .phoneNumber(authCode.getPhoneNumber())
            .attemptCount(authentication.getAttemptCount())
            .verified(true)
            .ttl(authentication.getTtl())
            .build();
        authenticationPersistencePort.saveAuthentication(updatedAuthentication);
    }
}
