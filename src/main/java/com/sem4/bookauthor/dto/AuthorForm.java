package com.sem4.bookauthor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorForm {

    private Long id;

    @NotBlank(message = "Vui lòng nhập tên tác giả")
    @Size(max = 255, message = "Tên tác giả tối đa 255 ký tự")
    private String name;

    @NotBlank(message = "Vui lòng nhập email")
    @Email(message = "Email không đúng định dạng")
    @Size(max = 255, message = "Email tối đa 255 ký tự")
    private String email;

    @Size(max = 2000, message = "Tiểu sử tối đa 2000 ký tự")
    private String bio;
}
