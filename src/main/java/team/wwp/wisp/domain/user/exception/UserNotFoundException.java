package team.wwp.wisp.domain.user.exception;

import team.wwp.wisp.global.error.ErrorCode;
import team.wwp.wisp.global.error.exception.WispException;

public class UserNotFoundException extends WispException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
