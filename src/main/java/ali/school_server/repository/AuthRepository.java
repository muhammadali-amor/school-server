package ali.school_server.repository;

import ali.school_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByPhoneNumber(String phoneNumber);

    Optional<User> findUserByEmail(String email);

    UserDetails findByPhoneNumber(String phoneNumber);
//    User findUsersByEmail(String email);

    boolean existsUserByPhoneNumber(String phoneNumber);

    boolean existsUserByEmail(String email);
}
