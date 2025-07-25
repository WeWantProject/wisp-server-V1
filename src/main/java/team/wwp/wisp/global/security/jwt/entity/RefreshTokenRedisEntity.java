package team.wwp.wisp.global.security.jwt.entity;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@RedisHash("refresh-token")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RefreshTokenRedisEntity {
    
    @Id
    private String token;

    @Indexed
    private String email;

    @TimeToLive(unit = TimeUnit.SECONDS)
    private Long expiration;
}
