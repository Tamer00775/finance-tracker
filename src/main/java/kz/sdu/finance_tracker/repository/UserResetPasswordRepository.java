package kz.sdu.finance_tracker.repository;

import kz.sdu.finance_tracker.entity.User;
import kz.sdu.finance_tracker.entity.UserResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserResetPasswordRepository extends JpaRepository<UserResetPassword, Long> {

    Optional<UserResetPassword> findByUserAndResettedIsTrue(User user);

    Optional<UserResetPassword> findByTemporaryPasswordAndResettedFalse(String token);
}
