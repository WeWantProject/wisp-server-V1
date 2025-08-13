package team.wwp.wisp.global.security.jwt.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.user.domain.User;
import team.wwp.wisp.domain.user.port.UserPersistencePort;
import team.wwp.wisp.global.security.exception.UserUnauthorizedException;

@Component
@RequiredArgsConstructor
public class CurrentUserProvider {
    
    private final UserPersistencePort userPersistencePort;

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof String phoneNumber) {
            return userPersistencePort.findUserByPhoneNumber(phoneNumber);
        }

        throw new UserUnauthorizedException();
    }
}
