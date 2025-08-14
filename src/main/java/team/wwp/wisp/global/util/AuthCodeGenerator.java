package team.wwp.wisp.global.util;

import java.security.SecureRandom;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthCodeGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();
    
    public String generateAuthCode() {
        int n = secureRandom.nextInt(1_000_000);
        return String.format("%06d", n);
    }

}
