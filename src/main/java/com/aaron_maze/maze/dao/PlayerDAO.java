package com.aaron_maze.maze.dao;

import com.aaron_maze.maze.model.Player;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PlayerDAO {
    private final Map<String, Player> players = new HashMap<>();

    public boolean existsByName(String name) {
        return players.containsKey(name);
    }

    public void save(Player player) {
        players.put(player.getName(), player);
    }

    public Player findByName(String name) {
        return players.get(name);
    }

    private final JdbcTemplate jdbcTemplate;

    public PlayerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isPlayerExists(String name) {
        String sql = "SELECT COUNT(*) FROM Player WHERE name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name);
        return count != null && count > 0;
    }

    public void registerPlayer(String name, String password) {
        String sql = "INSERT INTO Player (name, password) VALUES (?, ?)";
        jdbcTemplate.update(sql, name, password);
    }

    public boolean validateLogin(String name, String password) {
        String sql = "SELECT COUNT(*) FROM Player WHERE name = ? AND password = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name, password);
        return count != null && count > 0;
    }
}
