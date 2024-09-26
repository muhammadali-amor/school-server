package ali.school_server.entity;

import ali.school_server.entity.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private RoleName roleName;

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName.name();
    }
}
