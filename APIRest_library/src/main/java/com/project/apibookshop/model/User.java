package com.project.apibookshop.model;

import com.project.apibookshop.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;
        private String username;
        private String name;
        private String surname;
        private String password;

        @Enumerated(EnumType.STRING)
        private UserRole role;

        private String email;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Loan> loans = new ArrayList<>();

        private LocalDateTime registrationDate;
        private LocalDateTime updateDate;
}
