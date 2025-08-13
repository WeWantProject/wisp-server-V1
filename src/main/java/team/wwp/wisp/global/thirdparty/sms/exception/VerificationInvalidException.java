package team.wwp.wisp.global.thirdparty.sms.exception;

import team.wwp.wisp.global.error.ErrorCode;
import team.wwp.wisp.global.error.exception.WispException;

public class VerificationInvalidException extends WispException {
    public VerificationInvalidException() {
        super(ErrorCode.VERIFICATION_INVALID);
    }
}
