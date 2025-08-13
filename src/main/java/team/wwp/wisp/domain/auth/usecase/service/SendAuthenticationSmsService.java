package team.wwp.wisp.domain.auth.usecase.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.auth.domain.AuthCode;
import team.wwp.wisp.domain.auth.domain.Authentication;
import team.wwp.wisp.domain.auth.exception.PhoneAuthAttemptExceededException;
import team.wwp.wisp.domain.auth.port.AuthCodePersistencePort;
import team.wwp.wisp.domain.auth.port.AuthenticationPersistencePort;
import team.wwp.wisp.domain.auth.usecase.SendAuthenticationSmsUseCase;
import team.wwp.wisp.global.util.AuthCodeGenerator;
import team.wwp.wisp.global.util.SmsUtil;

@Service
@RequiredArgsConstructor
public class SendAuthenticationSmsService implements SendAuthenticationSmsUseCase {
    private final SmsUtil smsUtil;
    private final AuthCodePersistencePort authCodePersistencePort;
    private final AuthenticationPersistencePort authenticationPersistencePort;

    @Value("${coolsms.ttl}")
    private Long ttl;

    public void execute(String phoneNumber) {
        if(authenticationPersistencePort.existsAuthenticationByPhoneNumber(phoneNumber)) {
            Authentication authentication = authenticationPersistencePort.findAuthenticationByPhoneNumber(phoneNumber);
            if (authentication.getAttemptCount() >= 5) {
                throw new PhoneAuthAttemptExceededException();
             }
            Authentication updatedAuthentication = Authentication.builder()
                .phoneNumber(authentication.getPhoneNumber())
                .attemptCount(authentication.getAttemptCount() + 1)
                .verified(authentication.getVerified())
                .ttl(authentication.getTtl())
            .build();
            authenticationPersistencePort.saveAuthentication(updatedAuthentication);
        } else {
            Authentication newAuthentication = Authentication.builder()
                .phoneNumber(phoneNumber)
                .attemptCount(1)
                .verified(false)
                .ttl(ttl)
                .build();
            authenticationPersistencePort.saveAuthentication(newAuthentication);
        }
        AuthCode authCode = AuthCode.builder()
            .phoneNumber(phoneNumber)
            .authCode(AuthCodeGenerator.generateAuthCode())
            .ttl(ttl)
            .build();
        authCodePersistencePort.saveAuthCode(authCode);
        smsUtil.sendSms(phoneNumber, authCode.getAuthCode());
    }
}
