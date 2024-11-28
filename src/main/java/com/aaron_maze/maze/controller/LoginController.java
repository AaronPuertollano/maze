package com.aaron_maze.maze.controller;

import com.aaron_maze.maze.model.Maze;
import com.aaron_maze.maze.services.LoginService;
import com.aaron_maze.maze.services.MazeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    private MazeService mazeService;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name, @RequestParam String password, Model model) {

        if (name == null || name.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Los campos no pueden estar vacíos.");
            return "register";
        }

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

        //estas cosas lo tienen que hacer el service
        if (name == null || name.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "Los campos no pueden estar vacíos.");
            return "login";
        }

        if (loginService.validateLogin(name, password)) {
            session.setAttribute("user", name); // Guarda el usuario en la sesión
            return "redirect:/menu"; // Redirige al menú
        } else {
            model.addAttribute("error", "Credenciales inválidas. Inténtalo de nuevo.");
            return "login";
        }
    }

    @GetMapping("/menu")
    public String privat(HttpSession session, Model model) {
        String user = (String) session.getAttribute("user");

        if (user == null) {
            // Redirige al login si no hay usuario en sesión
            return "redirect:/login";
        }

        // Agregar el usuario al modelo
        model.addAttribute("user", user);

        // Obtener la lista de mazes
        List<Maze> mazes = mazeService.getAllMazes();
        if (mazes != null) {
            model.addAttribute("mazes", mazes);
        } else {
            model.addAttribute("mazes", new ArrayList<>()); // Manejo en caso de que sea null
        }

        return "menu";
    }

    @PostMapping("/start")
    public String startGame(@RequestParam int mazeId, HttpSession session) {
        session.setAttribute("selectedMaze", mazeId);
        return "redirect:/game";
    }
}
