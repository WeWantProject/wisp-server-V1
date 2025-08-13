package team.wwp.wisp.domain.auth.port;

import team.wwp.wisp.domain.auth.domain.Authentication;

public interface AuthenticationPersistencePort {
    Authentication saveAuthentication(Authentication authentication);
    Authentication findAuthenticationByPhoneNumber(String phoneNumber);
    Boolean existsAuthenticationByPhoneNumber(String phoneNumber);
}
