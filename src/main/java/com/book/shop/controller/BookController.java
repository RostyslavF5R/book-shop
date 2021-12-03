package com.book.shop.controller;

import com.book.shop.dto.BookDto;
import com.book.shop.model.Book;
import com.book.shop.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {
    private final ModelMapper modelMapper;
    private final BookService bookService;

    public BookController(ModelMapper modelMapper, BookService bookService) {
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks().stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{authorName}")
    public List<BookDto> getAllBooksByAuthorName(@PathVariable String authorName) {
        return bookService.getAllBooksByAuthorName(authorName).stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/most-selling-book/{authorName}")
    public ResponseEntity<BookDto> getMostSellingBook(@PathVariable String authorName) {
        Book book = bookService.getMostSellingBookByAuthorName(authorName);
        BookDto bookResponse = modelMapper.map(book, BookDto.class);
        return ResponseEntity.ok().body(bookResponse);
    }
    @GetMapping("/most-published-book/{authorName}")
    public ResponseEntity<BookDto> getMostPublishedBook(@PathVariable String authorName) {
        Book book = bookService.getMostPublishedBookByAuthorName(authorName);
        BookDto bookResponse = modelMapper.map(book, BookDto.class);
        return ResponseEntity.ok().body(bookResponse);
    }

    @GetMapping("/most-selling-books/{partName}")
    public List<BookDto> getMostSellingBooks(@PathVariable String partName) {
        return bookService.getMostSellingBooks(partName).stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/most-published-books/{partName}")
    public List<BookDto> getMostPublishedBooks(@PathVariable String partName) {
        return bookService.getMostPublishedBooks(partName).stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/most-successful-books/{partName}")
    public List<BookDto> getMostSuccessfulBooks(@PathVariable String partName) {
        return bookService.getMostSuccessfulBooks(partName).stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
        Book bookRequest = modelMapper.map(bookDto,Book.class);
        Book book = bookService.create(bookRequest);
        BookDto bookResponse = modelMapper.map(book,BookDto.class);
        return new ResponseEntity<BookDto>(bookResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id,
                                            @RequestBody BookDto bookDto) {
        Book bookRequest = modelMapper.map(bookDto, Book.class);
        Book book = bookService.update(bookRequest);
        BookDto bookResponse = modelMapper.map(book, BookDto.class);
        return ResponseEntity.ok().body(bookResponse);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

}
