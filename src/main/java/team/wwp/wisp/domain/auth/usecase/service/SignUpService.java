package team.wwp.wisp.domain.auth.usecase.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.auth.domain.Authentication;
import team.wwp.wisp.domain.auth.exception.UserExistsException;
import team.wwp.wisp.domain.auth.exception.UserForbiddenException;
import team.wwp.wisp.domain.auth.port.AuthenticationPersistencePort;
import team.wwp.wisp.domain.auth.usecase.SignUpUseCase;
import team.wwp.wisp.domain.user.domain.User;
import team.wwp.wisp.domain.user.domain.constant.UserRole;
import team.wwp.wisp.domain.user.domain.constant.UserStatus;
import team.wwp.wisp.domain.user.port.UserPersistencePort;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {
    
    private final AuthenticationPersistencePort authenticationPersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void execute(String username, String phoneNumber, String password, String displayName) {
        Authentication authentication = authenticationPersistencePort.findAuthenticationByPhoneNumber(phoneNumber);
        if (authentication == null || Boolean.FALSE.equals(authentication.getVerified())) {
            throw new UserForbiddenException();
        }
        if (userPersistencePort.existsUserByPhoneNumber(phoneNumber)) {
            throw new UserExistsException();
        }

        User user = User.builder()
            .username(username)
            .phoneNumber(phoneNumber)
            .password(bCryptPasswordEncoder.encode(password))
            .displayName(displayName)
            .profileImageUrl(null)
            .isActive(true)
            .role(UserRole.ROLE_USER)
            .status(UserStatus.ACTIVE)
            .build();
        userPersistencePort.saveUser(user);
    }
}
