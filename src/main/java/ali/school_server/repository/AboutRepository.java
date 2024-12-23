package ali.school_server.repository;

import ali.school_server.entity.About;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AboutRepository extends JpaRepository<About, Integer> {
    Optional<About> findFirstByOrderById();
}