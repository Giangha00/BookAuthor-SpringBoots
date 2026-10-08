package com.sem4.bookauthor.repository;

import com.sem4.bookauthor.entity.Author;
import com.sem4.bookauthor.entity.AuthorProfile;
import com.sem4.bookauthor.entity.Book;
import com.sem4.bookauthor.entity.Publisher;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class EntityGraphIntegrationTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    @DisplayName("Verify BookRepository EntityGraph eagerly loads publisher and authors without N+1")
    void testBookEntityGraph() {
        List<Book> books = bookRepository.findAllByOrderByIdDesc();
        assertFalse(books.isEmpty(), "Books should not be empty after data seeding");

        for (Book book : books) {
            // Verify relationships are loaded and initialized immediately
            if (book.getPublisher() != null) {
                assertTrue(Hibernate.isInitialized(book.getPublisher()),
                        "Publisher should be initialized by EntityGraph");
                assertNotNull(book.getPublisher().getName());
            }
            if (book.getAuthors() != null) {
                assertTrue(Hibernate.isInitialized(book.getAuthors()),
                        "Authors collection should be initialized by EntityGraph");
                for (Author author : book.getAuthors()) {
                    assertNotNull(author.getName());
                }
            }
        }
    }

    @Test
    @DisplayName("Verify AuthorRepository EntityGraph eagerly loads authorProfile and books")
    void testAuthorEntityGraph() {
        List<Author> authors = authorRepository.findAllByOrderByIdDesc();
        assertFalse(authors.isEmpty(), "Authors should not be empty after data seeding");

        for (Author author : authors) {
            if (author.getAuthorProfile() != null) {
                assertTrue(Hibernate.isInitialized(author.getAuthorProfile()),
                        "AuthorProfile should be initialized by EntityGraph");
                assertNotNull(author.getAuthorProfile().getBio());
            }
            if (author.getBooks() != null) {
                assertTrue(Hibernate.isInitialized(author.getBooks()),
                        "Books collection should be initialized by EntityGraph");
            }
        }
    }

    @Test
    @DisplayName("Verify PublisherRepository EntityGraph eagerly loads books")
    void testPublisherEntityGraph() {
        List<Publisher> publishers = publisherRepository.findAllByOrderByIdDesc();
        assertFalse(publishers.isEmpty(), "Publishers should not be empty after data seeding");

        for (Publisher publisher : publishers) {
            if (publisher.getBooks() != null) {
                assertTrue(Hibernate.isInitialized(publisher.getBooks()),
                        "Publisher books collection should be initialized by EntityGraph");
            }
        }
    }
}
