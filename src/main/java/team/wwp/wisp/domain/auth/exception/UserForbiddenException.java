package team.wwp.wisp.domain.auth.exception;

import team.wwp.wisp.global.error.ErrorCode;
import team.wwp.wisp.global.error.exception.WispException;

public class UserForbiddenException extends WispException {
    public UserForbiddenException() {
        super(ErrorCode.USER_FORBIDDEN);
    }
}
