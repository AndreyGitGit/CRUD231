package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.dao.UserDAO;
import web.model.User;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Получение списка пользователей из DAO.
     *
     * @param model - модель представления.
     * @return = возвращаемый резульат.
     */
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDAO.index());
        model.addAttribute("title", "Users Table");
        return "users/index";
    }

    /**
     * Получение пользователя по id из DAO.
     *
     * @param id    - id пользователя.
     * @param model - модель представления.
     * @return = возвращаемый резульат.
     */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.show(id));
        return "users/show";
    }

    /**
     * Метод создания пользователя.
     *
     * @param model - модель представления.
     * @return = возвращаемый резульат.
     */
    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    /**
     * Создание пользователя.
     *
     * @param user - пользователя для добавления в бд.
     * @return = возвращаемая страница.
     */
    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userDAO.save(user);
        return "redirect:/users";
    }
}
