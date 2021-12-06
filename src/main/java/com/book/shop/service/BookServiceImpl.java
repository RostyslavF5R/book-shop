package com.book.shop.service;

import com.book.shop.exception.ResourceNotFoundException;
import com.book.shop.model.Author;
import com.book.shop.model.Book;
import com.book.shop.repository.AuthorRepository;
import com.book.shop.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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
        Book exitingBook = bookRepository.findById(book.getId())
                .orElseThrow(() ->new ResourceNotFoundException("Cannot find book"));
        exitingBook.setBookName(book.getBookName());
        exitingBook.setPublishedAmount(book.getPublishedAmount());
        exitingBook.setSoldAmount(book.getSoldAmount());
        exitingBook.setAuthor(book.getAuthor());
        return exitingBook;
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
    public List<Book> getMostSellingBookByAuthorName(String authorName) {
        return bookRepository.findMostSellingBookByAuthorName(authorName);
    }

    @Override
    public List<Book> getMostPublishedBookByAuthorName(String authorName) {
        return bookRepository.findMostPublishedBookByAuthorName(authorName);
    }

    @Override
    public List<Book> getMostSellingBooks(String partName) {
        List<Author> authors = authorRepository.findAuthors(partName);
        List<Book> books = new ArrayList<>();
        for (Author author : authors) {
            List<Book> mostSellingBookByAuthorName = bookRepository
                    .findMostSellingBookByAuthorName(author.getAuthorName());
            books.addAll(mostSellingBookByAuthorName);
        }
        return books;
    }

    @Override
    public List<Book> getMostPublishedBooks(String partName) {
        List<Author> authors = authorRepository.findAuthors(partName);
        List<Book> books = new ArrayList<>();
        for (Author author : authors) {
            List<Book> mostSellingBookByAuthorName = bookRepository
                    .findMostPublishedBookByAuthorName(author.getAuthorName());
            books.addAll(mostSellingBookByAuthorName);
        }
        return books;
    }

    @Override
    public List<Book> getMostSuccessfulBooks(String partName) {
        List<Author> authors = authorRepository.findAuthors(partName);
        List<Book> books = new ArrayList<>();
        for(Author author : authors) {
            List<Book> bookByRating = bookRepository.findBookByRating(author.getAuthorName());
            books.addAll(bookByRating);
        }
        return books;
    }
}
