package ali.school_server.repository;

import ali.school_server.entity.Stories;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StoriesRepository extends JpaRepository<Stories, Integer> {
}
