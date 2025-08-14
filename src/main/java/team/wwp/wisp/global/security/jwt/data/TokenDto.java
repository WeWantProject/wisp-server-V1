package team.wwp.wisp.global.security.jwt.data;

import java.time.LocalDateTime;

public record TokenDto(
    String token,
    LocalDateTime expiration) {
}
