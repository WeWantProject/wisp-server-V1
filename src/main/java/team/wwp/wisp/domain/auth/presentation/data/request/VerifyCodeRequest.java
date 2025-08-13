package team.wwp.wisp.domain.auth.presentation.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VerifyCodeRequest(
    @NotBlank @Size(min=8, max=8) String code
) {
    
}
