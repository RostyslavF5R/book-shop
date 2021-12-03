package com.book.shop.repository;

import com.book.shop.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("FROM Book b JOIN FETCH b.author a WHERE a.authorName = :name")
    List<Book> findAllBooksByAuthorName(@Param("name") String authorName);

    @Query(value = "select * from books b join authors a on b.author_id = a.id "
            + "where b.sold_amount = "
            + "(select max(b.sold_amount) from books b join authors a on b.author_id = a.id "
            + "where a.name = ?1)", nativeQuery = true)
    Book getMostSellingBookByAuthorName(String authorName);

    @Query(value = "select * from books b join authors a on b.author_id = a.id "
            + "where b.published_amount = "
            + "(select max(b.published_amount) from books b join authors a on b.author_id = a.id "
            + "where a.name = ?1)", nativeQuery = true)
    Book getMostPublishedBookByAuthorName(String authorName);

    @Query(value = "select * from books b join authors a on b.author_id = a.id "
            + "where a.name = ?1 and published_amount / b.sold_amount = "
            + "(select min(published_amount / b.sold_amount) "
            + "from books b join authors a on b.author_id = a.id "
            + "where a.name = ?1)", nativeQuery = true)
    List<Book> findBookByRating(String partName);
}
