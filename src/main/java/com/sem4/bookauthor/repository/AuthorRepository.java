package com.sem4.bookauthor.repository;

import com.sem4.bookauthor.entity.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Override
    @EntityGraph(attributePaths = { "authorProfile", "books" })
    Optional<Author> findById(Long id);

    @Override
    @EntityGraph(attributePaths = { "authorProfile", "books" })
    List<Author> findAll();

    @EntityGraph(attributePaths = { "authorProfile", "books" })
    List<Author> findAllByOrderByIdDesc();

    @EntityGraph(attributePaths = { "authorProfile", "books" })
    List<Author> findByNameContainingIgnoreCaseOrderByIdDesc(String name);
}
