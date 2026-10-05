package com.sem4.bookauthor.dto;

public class PublisherDTO {
    private Long id;
    private String name;
    // Note: Not exposing books list to avoid circular references and large payloads
    // Client can fetch books separately if needed via book endpoint with publisher filter

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
}