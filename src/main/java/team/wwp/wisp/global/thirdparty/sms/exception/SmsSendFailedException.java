package team.wwp.wisp.global.thirdparty.sms.exception;

import team.wwp.wisp.global.error.ErrorCode;
import team.wwp.wisp.global.error.exception.WispException;

public class SmsSendFailedException extends WispException {
    public SmsSendFailedException() {
        super(ErrorCode.SMS_SEND_FAILED);
    }
}
