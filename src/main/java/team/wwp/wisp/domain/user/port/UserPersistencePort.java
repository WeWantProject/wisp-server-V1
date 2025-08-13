package team.wwp.wisp.domain.user.port;

import team.wwp.wisp.domain.user.domain.User;

public interface UserPersistencePort {
    User findUserByPhoneNumber(String phoneNumber);

    Boolean existsUserByPhoneNumber(String phoneNumber);

    User saveUser(User user);

    void updateUserPassword(Long userId, String newPassword);
}
