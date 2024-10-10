package ali.school_server.payload;

import lombok.*;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsDto {
    private String title;
    private String description;
    private File image;
    private String date;

}
