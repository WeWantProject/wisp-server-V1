package team.wwp.wisp.global.security.jwt.entity;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RedisHash(value = "refresh-token")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RefreshTokenRedisEntity {
    
    @Id
    private String token;

    @Indexed
    private String phoneNumber;

    @TimeToLive(unit = TimeUnit.SECONDS)
    private Long expiration;
}
