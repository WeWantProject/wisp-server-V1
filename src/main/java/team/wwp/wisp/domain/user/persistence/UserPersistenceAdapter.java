package team.wwp.wisp.domain.user.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import team.wwp.wisp.domain.user.domain.User;
import team.wwp.wisp.domain.user.entity.QUserJpaEntity;
import team.wwp.wisp.domain.user.exception.UserNotFoundException;
import team.wwp.wisp.domain.user.mapper.UserMapper;
import team.wwp.wisp.domain.user.port.UserPersistencePort;
import team.wwp.wisp.domain.user.repository.UserJpaRepository;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {
    
    private final UserJpaRepository userJpaRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final UserMapper userMapper;
    private static final QUserJpaEntity userJpaEntity = QUserJpaEntity.userJpaEntity;

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(userJpaEntity)
                        .where(userJpaEntity.phoneNumber.eq(phoneNumber))
                        .fetchOne()
        ).map(userMapper::toDomain).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Boolean existsUserByPhoneNumber(String phoneNumber) {
        var result = jpaQueryFactory
                .selectOne()
                .from(userJpaEntity)
                .where(userJpaEntity.phoneNumber.eq(phoneNumber))
                .fetchFirst();
        return result != null;
    }

    @Override
    public User saveUser(User user) {
        return userMapper.toDomain(userJpaRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public void updateUserPassword(Long userId, String newPassword) {
        jpaQueryFactory
                .update(userJpaEntity)
                .set(userJpaEntity.password, newPassword)
                .where(userJpaEntity.id.eq(userId))
                .execute();
    }
}
