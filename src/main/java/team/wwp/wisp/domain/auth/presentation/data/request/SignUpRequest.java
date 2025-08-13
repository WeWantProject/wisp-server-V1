package team.wwp.wisp.domain.auth.presentation.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(
    @NotBlank @Size(max=20) String username,
    @NotBlank @Size(min=11, max=11) String phoneNumber,
    @NotBlank @Size(min=8, max=20) String password,
    @NotBlank @Size(max=20) String displayName
) {
    
}
