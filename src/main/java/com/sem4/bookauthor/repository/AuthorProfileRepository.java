package com.sem4.bookauthor.repository;

import com.sem4.bookauthor.entity.AuthorProfile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorProfileRepository extends JpaRepository<AuthorProfile, Long> {

    @Override
    @EntityGraph(attributePaths = "author")
    Optional<AuthorProfile> findById(Long id);

    @Override
    @EntityGraph(attributePaths = "author")
    List<AuthorProfile> findAll();
}