package ali.school_server.payload;

import lombok.*;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AboutDto {
    private Integer id;
    private String title;
    private File image;
    private String description;
}
