package team.wwp.wisp.domain.user.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.wwp.wisp.domain.user.domain.constant.UserRole;
import team.wwp.wisp.domain.user.domain.constant.UserStatus;

@Entity
@Table(name="user_tbl")
@Getter
@NoArgsConstructor
public class UserJpaEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_name", nullable=false)
    private String username;

    @Column(name="phone_number", nullable=false, unique=true)
    private String phoneNumber;

    @Column(name="password", nullable=false)
    private String password;

    @Column(name="display_name", nullable=false)
    private String displayName;

    @Column(name="profile_image_url")
    private String profileImageUrl;

    @Column(name="status", nullable=false)
    @ColumnDefault("'OFFLINE'")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name="role", nullable=false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ROLE_USER'")
    private UserRole role;

    @Column(name="is_active")
    @ColumnDefault("true")
    private Boolean isActive;

    @Builder
    public UserJpaEntity(Long id, String username, String phoneNumber, String password, String displayName, String profileImageUrl, UserStatus status, UserRole role, Boolean isActive) {
        this.id = id;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.displayName = displayName;
        this.profileImageUrl = profileImageUrl;
        this.status = status;
        this.role = role;
        this.isActive = isActive;
    }
}
