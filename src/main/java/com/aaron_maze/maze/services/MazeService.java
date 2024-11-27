package com.aaron_maze.maze.services;

import com.aaron_maze.maze.dao.MazeDAO;
import com.aaron_maze.maze.model.Maze;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MazeService {

    @Autowired
    private MazeDAO mazeDAO;

    public List<Maze> getAllMazes() {
        return mazeDAO.getAllMazes();
    }

    public Maze getMazeById(int idMaze) {
        return mazeDAO.getMazeById(idMaze);
    }


}
