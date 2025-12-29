package com.project.apibookshop;

import com.project.apibookshop.enums.UserRole;
import com.project.apibookshop.model.User;
import com.project.apibookshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String adminEmail = "admin@bookshop.com";

        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .email(adminEmail)
                    .password(passwordEncoder.encode("admin1234"))
                    .role(UserRole.ADMIN)
                    .registrationDate(LocalDateTime.now())
                    .build();

            userRepository.save(admin);
            System.out.println("--- Usuario Admin inicial creado con éxito ---");
        }
    }

}
