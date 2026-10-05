package com.sem4.bookauthor.service;

import com.sem4.bookauthor.dto.AuthorDTO;
import com.sem4.bookauthor.dto.AuthorForm;
import com.sem4.bookauthor.entity.Author;
import com.sem4.bookauthor.entity.AuthorProfile;
import com.sem4.bookauthor.entity.Book;
import com.sem4.bookauthor.exception.ResourceNotFoundException;
import com.sem4.bookauthor.mapper.AuthorMapper;
import com.sem4.bookauthor.repository.AuthorRepository;
import com.sem4.bookauthor.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final AuthorProfileService authorProfileService;

    @Autowired
    public AuthorService(AuthorRepository authorRepository,
                         BookRepository bookRepository,
                         AuthorProfileService authorProfileService) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.authorProfileService = authorProfileService;
    }

    public List<AuthorDTO> search(String keyword) {
        List<Author> authors = isBlank(keyword)
                ? authorRepository.findAllByOrderByIdDesc()
                : authorRepository.findByNameContainingIgnoreCaseOrderByIdDesc(keyword.trim());
        return authors.stream().map(AuthorMapper::toDto).toList();
    }

    public AuthorDTO findById(Long id) {
        return AuthorMapper.toDto(getEntity(id));
    }

    public AuthorForm findFormById(Long id) {
        return AuthorMapper.toForm(getEntity(id));
    }

    @Transactional
    public AuthorDTO save(AuthorForm form) {
        Author author = form.getId() == null ? new Author() : getEntity(form.getId());
        author.setName(form.getName().trim());
        author.setEmail(form.getEmail().trim());
        Author saved = authorRepository.save(author);
        syncProfile(saved, form.getBio());
        return AuthorMapper.toDto(saved);
    }

    @Transactional
    public void deleteById(Long id) {
        Author author = getEntity(id);
        for (Book book : bookRepository.findByAuthorsId(id)) {
            book.getAuthors().removeIf(item -> id.equals(item.getId()));
        }
        if (author.getBooks() != null) {
            author.getBooks().clear();
        }
        if (author.getAuthorProfile() != null) {
            Long profileId = author.getAuthorProfile().getId();
            author.setAuthorProfile(null);
            authorProfileService.deleteById(profileId);
        }
        authorRepository.flush();
        authorRepository.delete(author);
    }

    public long count() {
        return authorRepository.count();
    }

    private void syncProfile(Author author, String bio) {
        String normalizedBio = bio == null ? "" : bio.trim();
        AuthorProfile profile = author.getAuthorProfile();
        if (normalizedBio.isEmpty()) {
            if (profile != null) {
                author.setAuthorProfile(null);
                authorProfileService.deleteById(profile.getId());
            }
            return;
        }
        if (profile == null) {
            profile = new AuthorProfile();
            profile.setAuthor(author);
            author.setAuthorProfile(profile);
        }
        profile.setBio(normalizedBio);
        authorProfileService.save(profile);
    }

    private Author getEntity(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy tác giả"));
    }

    private boolean isBlank(String keyword) {
        return keyword == null || keyword.isBlank();
    }
}
