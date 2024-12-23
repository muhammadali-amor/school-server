package ali.school_server.payload;

import lombok.*;

import java.io.File;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDto {
    private int id;
    private String name;
    private String description;
    private String photoId;
    private String date;

}
