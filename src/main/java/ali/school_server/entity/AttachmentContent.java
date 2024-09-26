package ali.school_server.entity;

import ali.school_server.entity.templates.AbsEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
public class AttachmentContent extends AbsEntity {
    @OneToOne
    private Attachment attachment;

    private byte[] bytes;
}
