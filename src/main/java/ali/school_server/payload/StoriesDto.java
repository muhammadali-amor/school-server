package ali.school_server.payload;

import lombok.*;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoriesDto {
    private Integer id;
    private String name;
    private File image;
    private File video;
}
