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
public class News extends AbsEntity {

    @Column(nullable = false)
    private String title;

    private File photo;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String date;
}
