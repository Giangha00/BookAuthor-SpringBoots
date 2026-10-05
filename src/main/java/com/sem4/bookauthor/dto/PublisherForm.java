package com.sem4.bookauthor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublisherForm {

    private Long id;

    @NotBlank(message = "Vui lòng nhập tên nhà xuất bản")
    @Size(max = 255, message = "Tên nhà xuất bản tối đa 255 ký tự")
    private String name;
}
