package com.aaron_maze.maze.services;

import com.aaron_maze.maze.dao.PlayerDAO;
import com.aaron_maze.maze.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    PlayerDAO playerDAO;

    @Autowired
    public LoginService(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public boolean registerPlayer(String name, String password) {
        if (playerDAO.isPlayerExists(name)) {
            return false;
        }
        playerDAO.registerPlayer(name, password);
        return true;
    }

    public boolean validateLogin(String name, String password) {
        return playerDAO.validateLogin(name, password);
    }

}
