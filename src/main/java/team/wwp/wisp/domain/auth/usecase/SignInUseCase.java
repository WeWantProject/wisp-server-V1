package team.wwp.wisp.domain.auth.usecase;

import team.wwp.wisp.domain.auth.presentation.data.response.AuthTokenResponse;

public interface SignInUseCase {
    AuthTokenResponse execute(String phoneNumber, String password);
}
