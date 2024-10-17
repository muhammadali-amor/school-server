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
    private String title;
    private String description;
    private UUID image;
    private String date;

}
