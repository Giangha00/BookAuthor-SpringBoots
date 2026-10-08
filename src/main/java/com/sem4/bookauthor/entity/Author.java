package com.sem4.bookauthor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "authors")
@Getter
@Setter
@NamedEntityGraph(name = "Author.full", attributeNodes = {
        @NamedAttributeNode("authorProfile"),
        @NamedAttributeNode("books")
})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    @OneToOne(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AuthorProfile authorProfile;
}
