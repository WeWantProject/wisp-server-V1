package team.wwp.wisp.global.util;

import java.security.SecureRandom;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthCodeGenerator {
    
    public String generateAuthCode() {
        return String.valueOf(100000 + new SecureRandom().nextInt(90000000));
    }

}
