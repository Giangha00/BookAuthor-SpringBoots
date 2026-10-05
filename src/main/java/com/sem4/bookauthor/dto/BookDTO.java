package com.sem4.bookauthor.dto;

import java.util.List;

public class BookDTO {
    private Long id;
    private String name;
    private String description;

    // Simplified publisher info to avoid circular reference
    private PublisherDTO publisher;

    // Simplified authors list with minimal info to avoid circular reference
    private List<AuthorSummaryDTO> authors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PublisherDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDTO publisher) {
        this.publisher = publisher;
    }

    public List<AuthorSummaryDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorSummaryDTO> authors) {
        this.authors = authors;
    }
}