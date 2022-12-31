package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    public User show(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id=:id", User.class
        );
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

    public void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    public void update(int id, User userUpdateInfo) {
        entityManager.merge(userUpdateInfo);
        entityManager.flush();
    }

    public void delete(int id) {
        User user = show(id);
        entityManager.remove(user);
        entityManager.flush();
    }
}
