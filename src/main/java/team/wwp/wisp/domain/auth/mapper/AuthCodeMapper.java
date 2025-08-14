package team.wwp.wisp.domain.auth.mapper;

import org.springframework.stereotype.Component;

import team.wwp.wisp.domain.auth.domain.AuthCode;
import team.wwp.wisp.domain.auth.entity.AuthCodeRedisEntity;
import team.wwp.wisp.global.mapper.GenericMapper;

@Component
public class AuthCodeMapper implements GenericMapper<AuthCodeRedisEntity, AuthCode> {
    
    @Override
    public AuthCodeRedisEntity toEntity(AuthCode authCode) {
        return AuthCodeRedisEntity.builder()
            .phoneNumber(authCode.getPhoneNumber())
            .authCode(authCode.getAuthCode())
            .ttl(authCode.getTtl())
            .build();
    }

    @Override
    public AuthCode toDomain(AuthCodeRedisEntity authCodeRedisEntity) {
        return AuthCode.builder()
            .phoneNumber(authCodeRedisEntity.getPhoneNumber())
            .authCode(authCodeRedisEntity.getAuthCode())
            .ttl(authCodeRedisEntity.getTtl())
            .build();
    }
}
