package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    private static long USER_COUNT;
    private final List<User> users;

    {
        users = new ArrayList<>();

        users.add(new User(++USER_COUNT, "Ben", "Dark", 25));
        users.add(new User(++USER_COUNT, "Ben1", "Dark1", 251));
        users.add(new User(++USER_COUNT, "Ben2", "Dark2", 252));
    }

    public List<User> index() {
        return users;
    }

    public User show(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public void save(User user) {
        user.setId(++USER_COUNT);
        users.add(user);
    }

    public void update(int id, User userUpdateInfo) {
        User userForUpdate = show(id);

        userForUpdate.setName(userUpdateInfo.getName());
        userForUpdate.setSurname(userUpdateInfo.getSurname());
        userForUpdate.setAge(userUpdateInfo.getAge());
    }

    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
