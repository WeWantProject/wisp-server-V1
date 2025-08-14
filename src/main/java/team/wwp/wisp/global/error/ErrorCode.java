package team.wwp.wisp.global.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Auth
    AUTHENTICATION_NOT_FOUND("Authentication Not Found", HttpStatus.FORBIDDEN.value()),   
    USER_UNAUTHORIZED("User Not Authorized", HttpStatus.UNAUTHORIZED.value()),
    USER_FORBIDDEN("User Forbidden", HttpStatus.FORBIDDEN.value()),
    REFRESH_TOKEN_INVALID("RefreshToken Expired Or Invalid", HttpStatus.UNAUTHORIZED.value()),
    PHONE_AUTH_ATTEMPT_EXCEEDED("Phone Auth Attempt Exceeded", HttpStatus.TOO_MANY_REQUESTS.value()),
    PASSWORD_INVALID("Password Invalid", HttpStatus.UNAUTHORIZED.value()), 
    
    // User
    USER_NOT_FOUND("User Not Found", HttpStatus.NOT_FOUND.value()),
    USER_ALREADY_EXISTS("User Already Exists", HttpStatus.CONFLICT.value()),

    // SMS
    SMS_SEND_FAILED("SMS Send Failed", HttpStatus.SERVICE_UNAVAILABLE.value()),
    SMS_FORMAT_INVALID("SMS Format Invalid", HttpStatus.UNAUTHORIZED.value()),
    VERIFICATION_INVALID("Verification Invalid", HttpStatus.UNAUTHORIZED.value());

    private final String message;
    private final int status;
}
