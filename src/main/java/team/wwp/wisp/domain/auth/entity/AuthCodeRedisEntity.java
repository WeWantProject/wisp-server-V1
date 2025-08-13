package team.wwp.wisp.domain.auth.entity;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import org.springframework.data.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RedisHash(value = "auth_code")
@Getter
@NoArgsConstructor
public class AuthCodeRedisEntity {
    
    @Id
    private String phoneNumber;
    @Indexed
    private String authCode;
    @TimeToLive(unit = TimeUnit.SECONDS)
    private Long ttl;

    @Builder
    public AuthCodeRedisEntity(String phoneNumber, String authCode, Long ttl) {
        this.phoneNumber = phoneNumber;
        this.authCode = authCode;
        this.ttl = ttl;
    }
}
