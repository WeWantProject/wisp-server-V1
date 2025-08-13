package team.wwp.wisp.domain.auth.exception;

import team.wwp.wisp.global.error.ErrorCode;
import team.wwp.wisp.global.error.exception.WispException;

public class PhoneAuthAttemptExceededException extends WispException {
    public PhoneAuthAttemptExceededException() {
        super(ErrorCode.PHONE_AUTH_ATTEMPT_EXCEEDED);
    }
}
