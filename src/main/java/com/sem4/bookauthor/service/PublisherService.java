package com.sem4.bookauthor.service;

import com.sem4.bookauthor.dto.PublisherDTO;
import com.sem4.bookauthor.dto.PublisherForm;
import com.sem4.bookauthor.entity.Publisher;
import com.sem4.bookauthor.exception.BusinessException;
import com.sem4.bookauthor.exception.ResourceNotFoundException;
import com.sem4.bookauthor.mapper.PublisherMapper;
import com.sem4.bookauthor.repository.BookRepository;
import com.sem4.bookauthor.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository, BookRepository bookRepository) {
        this.publisherRepository = publisherRepository;
        this.bookRepository = bookRepository;
    }

    public List<PublisherDTO> search(String keyword) {
        List<Publisher> publishers = isBlank(keyword)
                ? publisherRepository.findAllByOrderByIdDesc()
                : publisherRepository.findByNameContainingIgnoreCaseOrderByIdDesc(keyword.trim());
        return publishers.stream().map(PublisherMapper::toDto).toList();
    }

    public PublisherDTO findById(Long id) {
        return PublisherMapper.toDto(getEntity(id));
    }

    public PublisherForm findFormById(Long id) {
        return PublisherMapper.toForm(getEntity(id));
    }

    @Transactional
    public PublisherDTO save(PublisherForm form) {
        Publisher publisher = form.getId() == null ? new Publisher() : getEntity(form.getId());
        publisher.setName(form.getName().trim());
        return PublisherMapper.toDto(publisherRepository.save(publisher));
    }

    @Transactional
    public void deleteById(Long id) {
        Publisher publisher = getEntity(id);
        if (bookRepository.countByPublisherId(id) > 0) {
            throw new BusinessException("Không thể xóa nhà xuất bản đang có sách. Hãy xóa hoặc chuyển các sách trước.");
        }
        publisherRepository.delete(publisher);
    }

    public long count() {
        return publisherRepository.count();
    }

    private Publisher getEntity(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhà xuất bản"));
    }

    private boolean isBlank(String keyword) {
        return keyword == null || keyword.isBlank();
    }
}
