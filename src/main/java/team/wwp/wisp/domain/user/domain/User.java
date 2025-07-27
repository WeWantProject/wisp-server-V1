package team.wwp.wisp.domain.user.domain;

import lombok.Builder;
import lombok.Getter;
import team.wwp.wisp.domain.user.domain.constant.UserRole;

@Getter
@Builder
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole role;
}
