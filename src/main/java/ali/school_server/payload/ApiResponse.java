package ali.school_server.payload;

import ali.school_server.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class ApiResponse<T> implements Serializable {
    private String message;
    private boolean success;
    private ResToken resToken;
    private User user;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}