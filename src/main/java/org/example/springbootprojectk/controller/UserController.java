package org.example.springbootprojectk.controller;



import org.example.springbootprojectk.dao.UserDAO;
import org.example.springbootprojectk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping("/")
    public String showUser(Model model) {
        List<User> users = userDAO.showUser();
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "show";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam Long id, Model model) {
        User user = userDAO.showUserById(id);
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute User user) {
        userDAO.editUser(user);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userDAO.addUser(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam(value = "id") Long id) {
        userDAO.deleteUser(id);
        return "redirect:/";
    }
}
