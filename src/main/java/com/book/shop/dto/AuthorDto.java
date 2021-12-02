package com.book.shop.dto;

import lombok.Data;

@Data
public class AuthorDto {
    private Long id;
    private String authorName;
    private String birthDate;
    private String phone;
    private String email;
}
