package ali.school_server.entity;

import ali.school_server.entity.templates.AbsNameEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Stories extends AbsNameEntity {

    private String photoOrVideoId;

    @Column(nullable = false)
    private LocalDateTime date;

    private String title;


}
