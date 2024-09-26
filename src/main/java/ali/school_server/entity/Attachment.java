package ali.school_server.entity;

import ali.school_server.entity.templates.AbsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Attachment extends AbsEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long size;
}
