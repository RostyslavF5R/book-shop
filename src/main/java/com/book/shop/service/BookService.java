package com.book.shop.service;

import com.book.shop.model.Book;

import java.util.List;

public interface BookService {
    Book create(Book book);

    List<Book> getAllBooks();

    Book update(Book book);

    void delete(Long id);

    List<Book> getAllBooksByAuthorName(String authorName);

    Book getMostSellingBook(String authorName);
}
