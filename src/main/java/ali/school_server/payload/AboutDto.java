package ali.school_server.payload;

import lombok.*;

import java.io.File;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AboutDto {
    private Integer id;
    private String title;
    private String name;
    private String photoId;
    private String description;

    private String incoming;
}
