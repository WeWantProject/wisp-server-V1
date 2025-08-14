package team.wwp.wisp.domain.user.domain;

import lombok.Builder;
import lombok.Getter;
import team.wwp.wisp.domain.user.domain.constant.UserRole;
import team.wwp.wisp.domain.user.domain.constant.UserStatus;

@Getter
@Builder
public class User {
    private Long id;
    private String username;
    private String phoneNumber;
    private String password;
    private String displayName;
    private String profileImageUrl;
    private Boolean isActive;
    private UserStatus status;
    private UserRole role;

}
