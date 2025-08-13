package team.wwp.wisp.domain.auth.exception;

import team.wwp.wisp.global.error.ErrorCode;
import team.wwp.wisp.global.error.exception.WispException;

public class UserExistsException extends WispException {
    public UserExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}