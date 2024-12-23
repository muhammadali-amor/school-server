package ali.school_server.payload;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComfortableDto {
    private Integer id;
    private String name;
    private String url;
    private String photoId;
}
