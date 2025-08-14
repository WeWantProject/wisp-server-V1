package team.wwp.wisp.domain.auth.exception;

import team.wwp.wisp.global.error.ErrorCode;
import team.wwp.wisp.global.error.exception.WispException;

public class RefreshTokenInvalidException extends WispException {
    public RefreshTokenInvalidException() {
        super(ErrorCode.REFRESH_TOKEN_INVALID);
    }
}
