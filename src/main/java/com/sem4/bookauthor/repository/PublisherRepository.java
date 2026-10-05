package com.sem4.bookauthor.repository;

import com.sem4.bookauthor.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    List<Publisher> findAllByOrderByIdDesc();

    List<Publisher> findByNameContainingIgnoreCaseOrderByIdDesc(String name);
}
