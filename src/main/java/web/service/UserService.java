package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User show(int id);

    void save(User user);

    void update(int id, User userUpdateInfo);

    void delete(int id);
}
