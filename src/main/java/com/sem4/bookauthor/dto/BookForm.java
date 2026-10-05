package com.sem4.bookauthor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookForm {

    private Long id;

    @NotBlank(message = "Vui lòng nhập tên sách")
    @Size(max = 255, message = "Tên sách tối đa 255 ký tự")
    private String name;

    @Size(max = 2000, message = "Mô tả tối đa 2000 ký tự")
    private String description;

    @NotNull(message = "Vui lòng chọn nhà xuất bản")
    private Long publisherId;

    private List<Long> authorIds = new ArrayList<>();
}
