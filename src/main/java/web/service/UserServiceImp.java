package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDAO;
import web.model.User;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {
    private final UserDAO userDAO;

    public UserServiceImp(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public User show(int id) {
        return userDAO.show(id);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDAO.save(user);
    }

    @Transactional
    @Override
    public void update(User userUpdateInfo) {
        userDAO.update(userUpdateInfo);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }
}
