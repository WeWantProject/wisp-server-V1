package team.wwp.wisp.domain.auth.exception;

import team.wwp.wisp.global.error.ErrorCode;
import team.wwp.wisp.global.error.exception.WispException;

public class PasswordInvalidException extends WispException {
    public PasswordInvalidException() {
        super(ErrorCode.PASSWORD_INVALID);
    }
}
