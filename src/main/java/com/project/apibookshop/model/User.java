package com.project.apibookshop.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;
        private String username;
        private String password;
        private String role;
        private String email;

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Loan> loans = new ArrayList<>();
}
