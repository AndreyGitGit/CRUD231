package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получение списка пользователей из DAO.
     *
     * @param model - модель представления.
     * @return = возвращаемая страница.
     */
    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
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
        model.addAttribute("user", userService.show(id));
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
        userService.save(user);
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
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    /**
     * метод обновления информации по пользователю.
     *
     * @param user - пользователь для обновления.
     * @param id   - id  пользоватлея.
     * @return = возвращаемая страница.
     */
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") int id) {
        userService.update(id, user);
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
        userService.delete(id);
        return "redirect:/users";
    }
}
