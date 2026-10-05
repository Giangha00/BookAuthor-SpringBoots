package com.sem4.bookauthor.repository;

import com.sem4.bookauthor.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @EntityGraph(attributePaths = {"publisher", "authors"})
    Optional<Book> findById(Long id);

    @EntityGraph(attributePaths = {"publisher", "authors"})
    List<Book> findAllByOrderByIdDesc();

    @EntityGraph(attributePaths = {"publisher", "authors"})
    List<Book> findByNameContainingIgnoreCaseOrderByIdDesc(String name);

    @EntityGraph(attributePaths = "authors")
    List<Book> findByAuthorsId(Long authorId);

    @EntityGraph(attributePaths = "authors")
    List<Book> findByPublisherIdOrderByIdDesc(Long publisherId);

    long countByPublisherId(Long publisherId);

    @Query("select b.publisher.id, count(b) from Book b group by b.publisher.id")
    List<Object[]> countGroupedByPublisher();
}
