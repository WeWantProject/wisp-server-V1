package team.wwp.wisp.global.security.exception;

import team.wwp.wisp.global.error.ErrorCode;
import team.wwp.wisp.global.error.exception.WispException;

public class UserUnauthorizedException extends WispException {
    public UserUnauthorizedException() {
        super(ErrorCode.USER_UNAUTHORIZED);
    }
}
