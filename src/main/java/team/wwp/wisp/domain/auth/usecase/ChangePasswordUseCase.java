package team.wwp.wisp.domain.auth.usecase;

public interface ChangePasswordUseCase {
    void execute(String phoneNumber, String newPassword);
}
