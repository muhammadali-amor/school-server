package ali.school_server.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    private UUID userId;
    @NotBlank(message = "Telefon raqam bo'lishi shart")
    private String phoneNumber;
    private String password;
}
