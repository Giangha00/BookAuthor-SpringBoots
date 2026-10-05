package com.sem4.bookauthor.mapper;

import com.sem4.bookauthor.dto.AuthorDTO;
import com.sem4.bookauthor.dto.AuthorForm;
import com.sem4.bookauthor.dto.AuthorProfileDTO;
import com.sem4.bookauthor.dto.AuthorSummaryDTO;
import com.sem4.bookauthor.entity.Author;
import com.sem4.bookauthor.entity.AuthorProfile;

import java.util.List;

public final class AuthorMapper {

    private AuthorMapper() {
    }

    public static AuthorDTO toDto(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setEmail(author.getEmail());
        dto.setAuthorProfile(toProfile(author.getAuthorProfile()));
        if (author.getBooks() == null) {
            dto.setBooks(List.of());
        } else {
            dto.setBooks(author.getBooks().stream().map(BookMapper::toSummary).toList());
        }
        return dto;
    }

    public static AuthorSummaryDTO toSummary(Author author) {
        AuthorSummaryDTO dto = new AuthorSummaryDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        return dto;
    }

    public static AuthorForm toForm(Author author) {
        AuthorForm form = new AuthorForm();
        form.setId(author.getId());
        form.setName(author.getName());
        form.setEmail(author.getEmail());
        if (author.getAuthorProfile() != null) {
            form.setBio(author.getAuthorProfile().getBio());
        }
        return form;
    }

    private static AuthorProfileDTO toProfile(AuthorProfile profile) {
        if (profile == null) {
            return null;
        }
        AuthorProfileDTO dto = new AuthorProfileDTO();
        dto.setId(profile.getId());
        dto.setBio(profile.getBio());
        return dto;
    }
}
