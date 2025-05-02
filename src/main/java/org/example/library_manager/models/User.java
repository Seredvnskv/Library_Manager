package org.example.library_manager.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue
    @Getter
    private Long id;

    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
}
