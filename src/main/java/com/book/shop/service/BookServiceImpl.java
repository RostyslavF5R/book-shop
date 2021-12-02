package com.book.shop.service;

import com.book.shop.exception.ResourceNotFoundException;
import com.book.shop.model.Book;
import com.book.shop.repository.BookRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book update(Book book) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Book exitingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find book"));
        bookRepository.delete(exitingBook);
    }

    @Override
    public List<Book> getAllBooksByAuthorName(String authorName) {
        return bookRepository.findAllBooksByAuthorName(authorName);
    }

    @Override
    public Book getMostSellingBook(String authorName) {
        return bookRepository.getMostSellingBook(authorName);
    }
}
