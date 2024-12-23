package ali.school_server.entity;

import ali.school_server.entity.templates.AbsNameEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.File;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comfortable extends AbsNameEntity {
    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String photoId;
}