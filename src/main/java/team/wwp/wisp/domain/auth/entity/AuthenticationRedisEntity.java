package team.wwp.wisp.domain.auth.entity;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RedisHash(value = "authentication")
@Getter
@NoArgsConstructor
public class AuthenticationRedisEntity {
    @Id
    private String phoneNumber;
    private Integer attemptCount;
    private Boolean verified;
    @TimeToLive(unit = TimeUnit.SECONDS)
    private Long ttl;

    @Builder
    public AuthenticationRedisEntity(String phoneNumber, Integer attemptCount, Boolean verified, Long ttl) {
        this.phoneNumber = phoneNumber;
        this.attemptCount = attemptCount;
        this.verified = verified;
        this.ttl = ttl;
    }
}
