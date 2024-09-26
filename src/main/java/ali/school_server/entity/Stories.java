package ali.school_server.entity;

import ali.school_server.entity.templates.AbsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Stories extends AbsEntity {
    private File photo;
    private File video;

    @Column(nullable = false)
    private String title;
}
