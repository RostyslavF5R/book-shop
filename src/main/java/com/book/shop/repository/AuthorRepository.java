package com.book.shop.repository;

import com.book.shop.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("FROM Author a WHERE a.authorName LIKE %?1%")
    List<Author> findAuthors(String partName);

    @Query(value = "SELECT a.id, a.name, a.date, a.email, a.phone "
            + "FROM authors a JOIN books b on a.id = b.author_id GROUP BY a.name "
            + "ORDER BY SUM(b.sold_amount / b.published_amount) / COUNT(*) DESC LIMIT 1",
            nativeQuery = true)
    List<Author> findMostSuccessfulAuthor();
}
