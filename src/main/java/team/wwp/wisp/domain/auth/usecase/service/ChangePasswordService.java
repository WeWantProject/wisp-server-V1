package team.wwp.wisp.domain.auth.usecase.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.auth.exception.UserForbiddenException;
import team.wwp.wisp.domain.auth.port.AuthenticationPersistencePort;
import team.wwp.wisp.domain.auth.usecase.ChangePasswordUseCase;
import team.wwp.wisp.domain.user.domain.User;
import team.wwp.wisp.domain.user.port.UserPersistencePort;

@Service
@RequiredArgsConstructor
@Transactional
public class ChangePasswordService implements ChangePasswordUseCase {
    
    private final AuthenticationPersistencePort authenticationPersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void execute(String phoneNumber, String newPassword) {
        User user = userPersistencePort.findUserByPhoneNumber(phoneNumber);
        if (!authenticationPersistencePort.findAuthenticationByPhoneNumber(user.getPhoneNumber()).getVerified()) {
            throw new UserForbiddenException();
        }
        
        userPersistencePort.updateUserPassword(user.getId(), bCryptPasswordEncoder.encode(newPassword));
    }
}
