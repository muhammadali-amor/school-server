package ali.school_server.payload;

import lombok.*;

import java.io.File;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComfortablesDto {
    private Integer id;
    private String title;
    private String url;
    private File image;
}
