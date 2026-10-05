package com.sem4.bookauthor.mapper;

import com.sem4.bookauthor.dto.BookDTO;
import com.sem4.bookauthor.dto.BookForm;
import com.sem4.bookauthor.dto.BookSummaryDTO;
import com.sem4.bookauthor.entity.Author;
import com.sem4.bookauthor.entity.Book;

import java.util.List;

public final class BookMapper {

    private BookMapper() {
    }

    public static BookDTO toDto(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        dto.setDescription(book.getDescription());
        if (book.getPublisher() != null) {
            dto.setPublisher(PublisherMapper.toDto(book.getPublisher()));
        }
        if (book.getAuthors() == null) {
            dto.setAuthors(List.of());
        } else {
            dto.setAuthors(book.getAuthors().stream().map(AuthorMapper::toSummary).toList());
        }
        return dto;
    }

    public static BookSummaryDTO toSummary(Book book) {
        BookSummaryDTO dto = new BookSummaryDTO();
        dto.setId(book.getId());
        dto.setName(book.getName());
        return dto;
    }

    public static BookForm toForm(Book book) {
        BookForm form = new BookForm();
        form.setId(book.getId());
        form.setName(book.getName());
        form.setDescription(book.getDescription());
        if (book.getPublisher() != null) {
            form.setPublisherId(book.getPublisher().getId());
        }
        if (book.getAuthors() != null) {
            form.setAuthorIds(book.getAuthors().stream().map(Author::getId).toList());
        }
        return form;
    }
}
