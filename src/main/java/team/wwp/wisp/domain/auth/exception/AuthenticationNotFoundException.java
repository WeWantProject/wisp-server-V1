package team.wwp.wisp.domain.auth.exception;

import team.wwp.wisp.global.error.ErrorCode;
import team.wwp.wisp.global.error.exception.WispException;

public class AuthenticationNotFoundException extends WispException {
    public AuthenticationNotFoundException() {
        super(ErrorCode.AUTHENTICATION_NOT_FOUND);
    }
}
