package com.sem4.bookauthor.service;

import com.sem4.bookauthor.entity.AuthorProfile;
import com.sem4.bookauthor.repository.AuthorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorProfileService {

    private final AuthorProfileRepository authorProfileRepository;

    @Autowired
    public AuthorProfileService(AuthorProfileRepository authorProfileRepository) {
        this.authorProfileRepository = authorProfileRepository;
    }

    public AuthorProfile save(AuthorProfile authorProfile) {
        return authorProfileRepository.save(authorProfile);
    }

    public Optional<AuthorProfile> findById(Long id) {
        return authorProfileRepository.findById(id);
    }

    public List<AuthorProfile> findAll() {
        return authorProfileRepository.findAll();
    }

    public void deleteById(Long id) {
        authorProfileRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return authorProfileRepository.existsById(id);
    }

    public long count() {
        return authorProfileRepository.count();
    }
}