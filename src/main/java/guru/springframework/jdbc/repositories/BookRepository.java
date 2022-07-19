package guru.springframework.jdbc.repositories;

import guru.springframework.jdbc.domain.Book;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;


public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "Select * from Book b  where b.title = :title2",nativeQuery = true)
    Book findBookByTitleNativeQuery(@Param("title2") String title);

    @Query("Select b from Book b  where b.title = :title2")
    Book findBookByTitleWithQueryNamed(@Param("title2") String title);
    @Query("Select b from Book b where b.title=?1")
    Optional<Book> findBookByTitleWithQuery(String title1);
    Optional<Book> findBookByTitle(String title);

    Book readByTitle(String title);

    @Nullable
    Book getByTitle(@Nullable String title);

    Stream<Book> findAllByTitleNotNull();
    Stream<Book> findAllByTitleIs(String title);

    @Async
    Future<Book> queryByTitle(String title);
    Book jpaNamed(@Param("title") String title);


    // Book findBookByJpaNamed(@Param("title") String title);
}
