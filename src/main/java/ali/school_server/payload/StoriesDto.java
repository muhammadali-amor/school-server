package ali.school_server.payload;

import lombok.*;

import java.io.File;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoriesDto {
    private Integer id;
    private String name;
    private UUID photoOrVideoId;
}
