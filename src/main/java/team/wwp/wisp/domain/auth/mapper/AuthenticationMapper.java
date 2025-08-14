package team.wwp.wisp.domain.auth.mapper;

import org.springframework.stereotype.Component;

import team.wwp.wisp.domain.auth.domain.Authentication;
import team.wwp.wisp.domain.auth.entity.AuthenticationRedisEntity;
import team.wwp.wisp.global.mapper.GenericMapper;

@Component
public class AuthenticationMapper implements GenericMapper<AuthenticationRedisEntity, Authentication> {
    
    @Override
    public AuthenticationRedisEntity toEntity(Authentication authentication) {
        return AuthenticationRedisEntity.builder()
            .phoneNumber(authentication.getPhoneNumber())
            .attemptCount(authentication.getAttemptCount())
            .verified(authentication.getVerified())
            .ttl(authentication.getTtl())
            .build();
    }

    @Override
    public Authentication toDomain(AuthenticationRedisEntity authenticationRedisEntity) {
        return Authentication.builder()
            .phoneNumber(authenticationRedisEntity.getPhoneNumber())
            .attemptCount(authenticationRedisEntity.getAttemptCount())
            .verified(authenticationRedisEntity.getVerified())
            .ttl(authenticationRedisEntity.getTtl())
            .build();
    }
}
