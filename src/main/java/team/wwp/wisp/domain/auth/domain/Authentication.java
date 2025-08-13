package team.wwp.wisp.domain.auth.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Authentication {
    private String phoneNumber;
    private Integer attemptCount;
    private Boolean verified;
    private Long ttl;
}
