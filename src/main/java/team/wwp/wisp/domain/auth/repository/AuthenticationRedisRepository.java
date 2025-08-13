package team.wwp.wisp.domain.auth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import team.wwp.wisp.domain.auth.entity.AuthenticationRedisEntity;

public interface AuthenticationRedisRepository extends CrudRepository<AuthenticationRedisEntity, String> {
    Optional<AuthenticationRedisEntity> findByPhoneNumber(String phoneNumber);
}
