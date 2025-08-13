package team.wwp.wisp.domain.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import team.wwp.wisp.domain.auth.entity.AuthCodeRedisEntity;

@Repository
public interface AuthCodeRedisRepository extends CrudRepository<AuthCodeRedisEntity, String> {
    
    AuthCodeRedisEntity findByAuthCode(String authCode);

    Boolean existsByAuthCode(String authCode);

    void deleteByAuthCode(String authCode);
}
