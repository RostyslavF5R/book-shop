package com.book.shop.service;

import com.book.shop.model.Book;
import java.util.List;

public interface BookService {
    Book create(Book book);

    List<Book> getAllBooks();

    Book update(Book book);

    void delete(Long id);

    List<Book> getAllBooksByAuthorName(String authorName);

    List<Book> getMostSellingBookByAuthorName(String authorName);

    List<Book> getMostPublishedBookByAuthorName(String authorName);

    List<Book> getMostSellingBooks(String partName);

    List<Book> getMostPublishedBooks(String partName);

    List<Book> getMostSuccessfulBooks(String partName);
}
