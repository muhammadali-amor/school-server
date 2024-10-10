package ali.school_server.repository;

import ali.school_server.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository  extends JpaRepository<News, Integer> {
}
