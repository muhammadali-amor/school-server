package ali.school_server.repository;

import ali.school_server.entity.Comfortable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComfortableRepository extends JpaRepository<Comfortable, Integer> {
    boolean existsComfortableByName(String name);
}