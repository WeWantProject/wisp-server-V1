package team.wwp.wisp.domain.user.domain.constant;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority{
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
