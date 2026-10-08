package com.sem4.bookauthor.repository;

import com.sem4.bookauthor.entity.Publisher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    @Override
    @EntityGraph(attributePaths = "books")
    Optional<Publisher> findById(Long id);

    @Override
    @EntityGraph(attributePaths = "books")
    List<Publisher> findAll();

    @EntityGraph(attributePaths = "books")
    List<Publisher> findAllByOrderByIdDesc();

    @EntityGraph(attributePaths = "books")
    List<Publisher> findByNameContainingIgnoreCaseOrderByIdDesc(String name);
}
