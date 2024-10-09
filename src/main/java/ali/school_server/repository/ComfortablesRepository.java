package ali.school_server.repository;

import ali.school_server.entity.Comfortables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComfortablesRepository extends JpaRepository<Comfortables, Integer> {
    boolean existsComfortablesByTitle(String title);
}