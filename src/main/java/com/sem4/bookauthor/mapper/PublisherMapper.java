package com.sem4.bookauthor.mapper;

import com.sem4.bookauthor.dto.PublisherDTO;
import com.sem4.bookauthor.dto.PublisherForm;
import com.sem4.bookauthor.entity.Publisher;

public final class PublisherMapper {

    private PublisherMapper() {
    }

    public static PublisherDTO toDto(Publisher publisher) {
        PublisherDTO dto = new PublisherDTO();
        dto.setId(publisher.getId());
        dto.setName(publisher.getName());
        return dto;
    }

    public static PublisherForm toForm(Publisher publisher) {
        PublisherForm form = new PublisherForm();
        form.setId(publisher.getId());
        form.setName(publisher.getName());
        return form;
    }
}
