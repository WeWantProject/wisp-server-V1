package team.wwp.wisp.global.security.jwt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import team.wwp.wisp.global.security.jwt.entity.RefreshTokenRedisEntity;

@Repository
public interface RefreshTokenRedisRepository extends CrudRepository<RefreshTokenRedisEntity, String> {
    
}
