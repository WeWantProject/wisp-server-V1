package team.wwp.wisp.domain.auth.persistence;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.auth.domain.Authentication;
import team.wwp.wisp.domain.auth.mapper.AuthenticationMapper;
import team.wwp.wisp.domain.auth.port.AuthenticationPersistencePort;
import team.wwp.wisp.domain.auth.repository.AuthenticationRedisRepository;

@Component
@RequiredArgsConstructor
public class AuthenticationPersistenceAdapter implements AuthenticationPersistencePort {
    
    private final AuthenticationRedisRepository authenticationRedisRepository;
    private final AuthenticationMapper authenticationMapper;

    @Override
    public Authentication findAuthenticationByPhoneNumber(String phoneNumber) {
        return authenticationMapper.toDomain(authenticationRedisRepository.findByPhoneNumber(phoneNumber).orElseThrow());
    }

    @Override
    public Boolean existsAuthenticationByPhoneNumber(String phoneNumber) {
        return authenticationRedisRepository.existsById(phoneNumber);
    }

    @Override
    public Authentication saveAuthentication(Authentication authentication) {
        return authenticationMapper.toDomain(authenticationRedisRepository.save(authenticationMapper.toEntity(authentication)));
    }
}
