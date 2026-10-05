package com.sem4.bookauthor.repository;

import com.sem4.bookauthor.entity.AuthorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorProfileRepository extends JpaRepository<AuthorProfile, Long> {
}