package guru.springframework.jdbc.dao;

import guru.springframework.jdbc.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AuthorDaoHibernate implements AuthorDao{
    EntityManagerFactory emf;

    public AuthorDaoHibernate(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Author getById(Long id) {
        return null;
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        return null;
    }

    @Override
    public void deleteAuthorById(Long id) {

    }

    @Override
    public List<Author> findAllAuthorsByLastName(String lastName, Pageable pageable) {
        String hql =  "select a from Author a where a.lastName= :lastName";
        if(pageable.getSort().getOrderFor("first_name") != null)
        {
            hql+= " order by a.firstName "
                    + pageable.getSort().getOrderFor("first_name").getDirection().name();

        }
        EntityManager em = getEntityManager();
        System.out.println("Hql is " + hql);

        try {
            TypedQuery<Author> query = em.createQuery(hql, Author.class);
            query.setParameter("lastName", lastName);
            query.setFirstResult(pageable.getPageNumber());
            query.setMaxResults(pageable.getPageSize());
            return query.getResultList();
        }
        finally {
            em.close();
        }
    }

    EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }
}
