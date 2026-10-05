package com.sem4.bookauthor.service;

import com.sem4.bookauthor.dto.BookDTO;
import com.sem4.bookauthor.dto.BookForm;
import com.sem4.bookauthor.dto.BookSummaryDTO;
import com.sem4.bookauthor.entity.Author;
import com.sem4.bookauthor.entity.Book;
import com.sem4.bookauthor.entity.Publisher;
import com.sem4.bookauthor.exception.ResourceNotFoundException;
import com.sem4.bookauthor.mapper.BookMapper;
import com.sem4.bookauthor.repository.AuthorRepository;
import com.sem4.bookauthor.repository.BookRepository;
import com.sem4.bookauthor.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository,
                       PublisherRepository publisherRepository,
                       AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookDTO> search(String keyword) {
        return load(keyword).stream().map(BookMapper::toDto).toList();
    }

    public List<BookSummaryDTO> findSummariesByPublisher(Long publisherId) {
        return bookRepository.findByPublisherIdOrderByIdDesc(publisherId).stream()
                .map(BookMapper::toSummary)
                .toList();
    }

    public Map<Long, Long> countByPublisher() {
        Map<Long, Long> counts = new HashMap<>();
        for (Object[] row : bookRepository.countGroupedByPublisher()) {
            counts.put((Long) row[0], (Long) row[1]);
        }
        return counts;
    }

    public BookDTO findById(Long id) {
        return BookMapper.toDto(getEntity(id));
    }

    public BookForm findFormById(Long id) {
        return BookMapper.toForm(getEntity(id));
    }

    @Transactional
    public BookDTO save(BookForm form) {
        Book book = form.getId() == null ? new Book() : getEntity(form.getId());
        book.setName(form.getName().trim());
        book.setDescription(normalize(form.getDescription()));
        book.setPublisher(getPublisher(form.getPublisherId()));
        syncAuthors(book, form.getAuthorIds());
        return BookMapper.toDto(bookRepository.save(book));
    }

    @Transactional
    public void deleteById(Long id) {
        Book book = getEntity(id);
        bookRepository.delete(book);
    }

    public long count() {
        return bookRepository.count();
    }

    private List<Book> load(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return bookRepository.findAllByOrderByIdDesc();
        }
        return bookRepository.findByNameContainingIgnoreCaseOrderByIdDesc(keyword.trim());
    }

    private void syncAuthors(Book book, List<Long> requestedIds) {
        List<Long> authorIds = new ArrayList<>();
        if (requestedIds != null) {
            for (Long authorId : requestedIds) {
                if (authorId != null && !authorIds.contains(authorId)) {
                    authorIds.add(authorId);
                }
            }
        }
        List<Author> selected = authorIds.isEmpty() ? new ArrayList<>() : authorRepository.findAllById(authorIds);
        if (selected.size() != authorIds.size()) {
            throw new ResourceNotFoundException("Không tìm thấy tác giả đã chọn");
        }
        if (book.getAuthors() == null) {
            book.setAuthors(new ArrayList<>());
        }
        book.getAuthors().clear();
        book.getAuthors().addAll(selected);
    }

    private Publisher getPublisher(Long publisherId) {
        return publisherRepository.findById(publisherId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhà xuất bản"));
    }

    private Book getEntity(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách"));
    }

    private String normalize(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return value.trim();
    }
}
