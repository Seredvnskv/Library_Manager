package org.example.library_manager.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String title;
    @Setter
    @Getter
    private String author;
    @Getter
    @Setter
    private int yearPublished;
}
