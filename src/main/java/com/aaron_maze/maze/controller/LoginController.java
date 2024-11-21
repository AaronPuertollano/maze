package com.aaron_maze.maze.controller;

import com.aaron_maze.maze.services.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name, @RequestParam String password, Model model) {
        boolean isRegistered = loginService.registerPlayer(name, password);
        if (isRegistered) {
            model.addAttribute("message", "Usuario registrado.");
            return "redirect:/login"; // Si ha ido bien, redirige al login
        } else {
            model.addAttribute("error", "El usuario ya existe.");
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String name, @RequestParam String password, HttpSession session, Model model) {
        // Valida las credenciales
        if (loginService.validateLogin(name, password)) {
            session.setAttribute("user", name); // Guarda el usuario en la sesión
            return "redirect:/menu"; // Redirige al menú
        } else {
            model.addAttribute("error", "Credenciales inválidas. Inténtalo de nuevo.");
            return "login";
        }
    }

    @GetMapping("/menu")
    public String showMenuPage(HttpSession session, Model model) {

        String user = (String) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);
        return "menu";
    }
}
