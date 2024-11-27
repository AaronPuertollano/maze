package com.aaron_maze.maze.dao;

import com.aaron_maze.maze.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MazeDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Maze> getAllMazes() {
        String sql = "SELECT id_maze, name FROM Maze";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Maze maze = new Maze();
            maze.setIdMaze(rs.getInt("id_maze"));
            maze.setName(rs.getString("name"));
            return maze;
        });
    }
    public Maze getMazeById(int idMaze) {
        String sql = "SELECT id_maze, name, description, id_start_room FROM Maze WHERE id_maze = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Maze maze = new Maze();
            maze.setIdMaze(rs.getInt("id_maze"));
            maze.setName(rs.getString("name"));
            maze.setDescription(rs.getString("description"));
            maze.setStartRoomId(rs.getInt("id_start_room"));
            return maze;
        }, idMaze);
    }

}
