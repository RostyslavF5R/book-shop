package com.book.shop.service;

import com.book.shop.exception.ResourceNotFoundException;
import com.book.shop.model.Author;

import java.util.List;

public interface AuthorService {
    Author create(Author author);

    List<Author> getAllAuthors();

    Author update(Author author);

    void delete(Long id);
}
