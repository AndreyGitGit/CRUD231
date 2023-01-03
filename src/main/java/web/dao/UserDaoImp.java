package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<User> getAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public void save(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }
    @Override
    public void update(User userUpdateInfo) {
        entityManager.merge(userUpdateInfo);
        entityManager.flush();
    }
    @Override
    public void delete(int id) {
        User user = show(id);
        entityManager.remove(user);
        entityManager.flush();
    }
}
