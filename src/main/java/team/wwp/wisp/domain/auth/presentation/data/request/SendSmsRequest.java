package team.wwp.wisp.domain.auth.presentation.data.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SendSmsRequest(
    @NotBlank @Size(min=11, max=11) String phoneNumber
) {
    
}
