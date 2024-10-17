package ali.school_server.entity;

import ali.school_server.entity.templates.AbsEntity;
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
public class Events extends AbsEntity {
    private UUID photo;

    @Column(nullable = false)
    private String date;

    private String location;

    @Column(nullable = false)
    private String description;
}
