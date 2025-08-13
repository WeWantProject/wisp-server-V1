package team.wwp.wisp.global.error.response;

public record ErrorResponse(
    String message,
    int httpStatus
) {
}
