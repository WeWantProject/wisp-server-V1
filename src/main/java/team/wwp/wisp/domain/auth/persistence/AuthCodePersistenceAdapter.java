package team.wwp.wisp.domain.auth.persistence;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.auth.domain.AuthCode;
import team.wwp.wisp.domain.auth.mapper.AuthCodeMapper;
import team.wwp.wisp.domain.auth.port.AuthCodePersistencePort;
import team.wwp.wisp.domain.auth.repository.AuthCodeRedisRepository;

@Component
@RequiredArgsConstructor
public class AuthCodePersistenceAdapter implements AuthCodePersistencePort {
    
    private final AuthCodeRedisRepository authCodeRedisRepository;
    private final AuthCodeMapper authCodeMapper;

    @Override
    public AuthCode saveAuthCode(AuthCode authCode) {
        return authCodeMapper.toDomain(authCodeRedisRepository.save(authCodeMapper.toEntity(authCode)));
    }
    @Override
    public Boolean existsAuthCodeByCode(String code) {
        return authCodeRedisRepository.existsByAuthCode(code);
    }
    @Override
    public AuthCode findAuthCodeByCode(String code) {
        return authCodeMapper.toDomain(authCodeRedisRepository.findByAuthCode(code));
    }
    @Override
    public void deleteAuthCodeByCode(String code) {
        authCodeRedisRepository.deleteByAuthCode(code);
    }
}
