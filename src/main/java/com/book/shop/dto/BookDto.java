package com.book.shop.dto;

import lombok.Data;

@Data
public class BookDto {
    private Long id;
    private String bookName;
    private int publishedAmount;
    private int soldAmount;
    private Long authorId;
}
