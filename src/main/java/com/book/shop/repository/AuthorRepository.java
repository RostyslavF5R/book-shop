package com.book.shop.repository;

import com.book.shop.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("FROM Author a WHERE a.authorName LIKE %?1%")
    List<Author> findAuthors(String partName);
}
