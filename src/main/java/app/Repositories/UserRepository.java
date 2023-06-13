package app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import app.Models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNicknameAndPswrd(String nickname, String pswrd);
}
