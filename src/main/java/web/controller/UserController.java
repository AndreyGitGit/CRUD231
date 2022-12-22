package web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
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
     * @return = возвращаемая страница.
     */
    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userDAO.index());
        model.addAttribute("title", "Users Table");
        return "users";
    }

    /**
     * Получение пользователя по id из DAO.
     *
     * @param id    - id пользователя.
     * @param model - модель представления.
     * @return = возвращаемая страница.
     */
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.show(id));
        return "show";
    }

    /**
     * Метод создания пользователя.
     *
     * @param user - пользователь.
     * @return = возвращаемая страница.
     */
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    /**
     * Создание пользователя.
     *
     * @param user - пользователя для добавления в бд.
     * @return = возвращаемая страница.
     */
    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "users/new";
        }
        userDAO.save(user);
        return "redirect:/users";
    }

    /**
     * Метод редактирования пользователя.
     *
     * @param model - модель представления.
     * @param id    - id  пользователя.
     * @return = возвращаемая страница.
     */
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAO.show(id));
        return "users/edit";
    }

    /**
     * метод обновления информации по пользователю.
     *
     * @param user - пользователь для обновления.
     * @param id   - id  пользоватлея.
     * @return = возвращаемая страница.
     */
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userDAO.update(id, user);
        return "redirect:/users";
    }

    /**
     * метод удаления пользователя по id.
     *
     * @param id - id пользователя для удаления.
     * @return = возвращаемая страница.
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDAO.delete(id);
        return "redirect:/users";
    }
}
