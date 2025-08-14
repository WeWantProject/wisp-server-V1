package team.wwp.wisp.domain.auth.usecase;

public interface SendAuthenticationSmsUseCase {
    void execute(String phoneNumber);
}
