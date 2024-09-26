package ali.school_server.payload;

import ali.school_server.entity.Role;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDto {
    private UUID id;
    private UUID photoId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private List<Role> roles;
    private Integer roleId;
    private String password;
    private String description;
    private String malumot;
}
