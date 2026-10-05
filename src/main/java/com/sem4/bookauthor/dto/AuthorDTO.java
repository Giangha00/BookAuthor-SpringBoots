package com.sem4.bookauthor.dto;

import java.util.List;

public class AuthorDTO {
    private Long id;
    private String name;
    private String email;

    // Simplified books list with minimal info to avoid circular reference
    private List<BookSummaryDTO> books;

    // Author profile info
    private AuthorProfileDTO authorProfile;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BookSummaryDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookSummaryDTO> books) {
        this.books = books;
    }

    public AuthorProfileDTO getAuthorProfile() {
        return authorProfile;
    }

    public void setAuthorProfile(AuthorProfileDTO authorProfile) {
        this.authorProfile = authorProfile;
    }
}