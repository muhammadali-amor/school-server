package ali.school_server.repository;

import ali.school_server.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    Attachment getAttachmentByName(String name);
}
