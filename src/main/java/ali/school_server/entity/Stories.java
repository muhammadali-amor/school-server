package ali.school_server.entity;

import ali.school_server.entity.templates.AbsNameEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.File;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Stories extends AbsNameEntity {
    private File photo;
    private File video;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime date;


}
