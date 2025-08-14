package team.wwp.wisp.global.error.exception;

import lombok.Getter;
import team.wwp.wisp.global.error.ErrorCode;

@Getter
public class WispException extends RuntimeException {
    private final ErrorCode errorCode;

    public WispException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
