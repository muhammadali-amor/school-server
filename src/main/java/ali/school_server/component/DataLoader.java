package ali.school_server.component;

import ali.school_server.entity.Role;
import ali.school_server.entity.User;
import ali.school_server.entity.enums.RoleName;
import ali.school_server.repository.AuthRepository;
import ali.school_server.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String intiMode;

    private final AuthRepository authRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (intiMode.equals("create") || intiMode.equals("create-drop")) {
            for (RoleName value : RoleName.values()) {
                roleRepository.save(new Role(value));
            }
            authRepository.save(
                    User.builder()
                            .name("Admin")
                            .surname("Adminov")
                            .phoneNumber("987654321")
                            .email("school@gmail.com")
                            .password(passwordEncoder.encode("root1234"))
                            .roles(new HashSet<>(roleRepository.findAll()))
                            .accountNonLocked(true)
                            .accountNonExpired(true)
                            .credentialsNonExpired(true)
                            .enabled(true)
                            .build()
            );
        }
    }
}
