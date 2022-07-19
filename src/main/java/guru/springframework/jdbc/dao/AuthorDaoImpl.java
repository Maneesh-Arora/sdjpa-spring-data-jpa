package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import guru.springframework.jdbc.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Created by jt on 8/28/21.
 */
@Component
public class AuthorDaoImpl implements AuthorDao {
    private final AuthorRepository authorRepository;

    public AuthorDaoImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {

        return authorRepository.findAuthorByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public Author saveNewAuthor(Author author) {

        return authorRepository.saveAndFlush(author);
    }

    @Override
    @Transactional
    public Author updateAuthor(Author author) {
        Author found = authorRepository.getById(author.getId());
        found.setFirstName(author.getFirstName());
        found.setLastName(author.getLastName());
        return authorRepository.saveAndFlush(found);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> findAllAuthorsByLastName(String lastName, Pageable pageable) {
        return authorRepository.findAuthorByLastName(lastName, pageable).getContent();
    }
}
