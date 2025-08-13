package team.wwp.wisp.domain.auth.port;

import team.wwp.wisp.domain.auth.domain.AuthCode;

public interface AuthCodePersistencePort {
    AuthCode saveAuthCode(AuthCode authCode);
    AuthCode findAuthCodeByCode(String authCode);
    Boolean existsAuthCodeByCode(String authCode);
    void deleteAuthCodeByCode(String authCode);
}
