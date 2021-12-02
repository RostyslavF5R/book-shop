package com.book.shop.service;

import com.book.shop.exception.ResourceNotFoundException;
import com.book.shop.model.Author;
import com.book.shop.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author update(Author author) {
        Author exitingAuthor = authorRepository.findById(author.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find author"));
        exitingAuthor.setAuthorName(author.getAuthorName());
        exitingAuthor.setBirthDate(author.getBirthDate());
        exitingAuthor.setPhone(author.getPhone());
        exitingAuthor.setEmail(author.getEmail());
        return authorRepository.save(exitingAuthor);
    }

    @Override
    public void delete(Long id) {
        Author exitingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find author"));
        authorRepository.delete(exitingAuthor);
    }
}
