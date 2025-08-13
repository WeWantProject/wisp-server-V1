package team.wwp.wisp.domain.auth.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthCode {
    private String phoneNumber;
    private String authCode;
    private Long ttl;
}
