package com.sem4.bookauthor.dto;

public class AuthorProfileDTO {
    private Long id;
    private String bio;
    // Note: Not exposing full author to avoid circular reference
    // Client can get author info from author endpoint if needed

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}