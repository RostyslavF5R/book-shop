package com.book.shop.controller;

import com.book.shop.dto.AuthorDto;
import com.book.shop.model.Author;
import com.book.shop.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {
    private final ModelMapper modelMapper;
    private final AuthorService authorService;

    public AuthorController(ModelMapper modelMapper, AuthorService authorService) {
        this.modelMapper = modelMapper;
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors().stream()
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/most-successful-author")
    public List<AuthorDto> getMost() {
        return authorService.getMostSuccessfulAuthor().stream()
                .map(author -> modelMapper.map(author, AuthorDto.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<AuthorDto> create(@RequestBody AuthorDto authorDto) {
        Author authorRequest = modelMapper.map(authorDto, Author.class);
        Author author = authorService.create(authorRequest);
        AuthorDto authorResponse = modelMapper.map(author, AuthorDto.class);
        return new ResponseEntity<AuthorDto>(authorResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDto> update(@PathVariable Long id,
                                            @RequestBody AuthorDto authorDto) {
        Author authorRequest = modelMapper.map(authorDto, Author.class);
        Author author = authorService.update(authorRequest);
        AuthorDto authorResponse = modelMapper.map(author, AuthorDto.class);
        return ResponseEntity.ok().body(authorResponse);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }

}
