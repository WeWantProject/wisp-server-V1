package team.wwp.wisp.domain.user.mapper;

import org.springframework.stereotype.Component;

import team.wwp.wisp.domain.user.domain.User;
import team.wwp.wisp.domain.user.entity.UserJpaEntity;
import team.wwp.wisp.global.mapper.GenericMapper;

@Component
public class UserMapper implements GenericMapper<UserJpaEntity, User> {
    
    @Override
    public UserJpaEntity toEntity(User user) {
        return UserJpaEntity.builder()
            .id(user.getId())
            .username(user.getUsername())
            .phoneNumber(user.getPhoneNumber())
            .password(user.getPassword())
            .displayName(user.getDisplayName())
            .profileImageUrl(user.getProfileImageUrl())
            .status(user.getStatus())
            .role(user.getRole())
            .isActive(user.getIsActive())
            .build();
    }

    @Override
    public User toDomain(UserJpaEntity userJpaEntity) {
        return User.builder()
            .id(userJpaEntity.getId())
            .username(userJpaEntity.getUsername())
            .phoneNumber(userJpaEntity.getPhoneNumber())
            .password(userJpaEntity.getPassword())
            .displayName(userJpaEntity.getDisplayName())
            .profileImageUrl(userJpaEntity.getProfileImageUrl())
            .status(userJpaEntity.getStatus())
            .role(userJpaEntity.getRole())
            .isActive(userJpaEntity.getIsActive())
            .build();
    }
}
