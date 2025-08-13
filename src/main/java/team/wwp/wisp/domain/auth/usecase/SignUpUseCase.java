package team.wwp.wisp.domain.auth.usecase;

public interface SignUpUseCase {
    void execute(String username, String phoneNumber, String password, String displayName);
}
