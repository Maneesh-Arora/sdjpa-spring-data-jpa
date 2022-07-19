package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import jakarta.persistence.EntityManagerFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.jdbc.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class AuthorDaoHibernateTest {

    @Autowired
    EntityManagerFactory entityManagerFactory;
    AuthorDao authorDao;
    @BeforeEach
    void setUp() {
        authorDao = new AuthorDaoHibernate(entityManagerFactory);
    }

    @Test
    void findAllAuthorsByLastName() {

        List<Author> authors = authorDao.findAllAuthorsByLastName("Smith", PageRequest.of(0,10
                ));

        Assertions.assertThat(authors).isNotNull();
        Assertions.assertThat(authors.size()).isGreaterThanOrEqualTo(4);

    }
    @Test
    void findAllAuthorsByLastNameDesc() {

        List<Author> authors = authorDao.findAllAuthorsByLastName("Smith", PageRequest.of(0,10,
                Sort.by(Sort.Order.desc("first_name"))));

        Assertions.assertThat(authors).isNotNull();
        Assertions.assertThat(authors.size()).isGreaterThanOrEqualTo(4);

    }
}