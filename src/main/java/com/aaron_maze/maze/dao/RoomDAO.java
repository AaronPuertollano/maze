package com.aaron_maze.maze.dao;

import com.aaron_maze.maze.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Room getRoomById(int roomId) {
        String sql = "SELECT * FROM Room WHERE id_room = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{roomId}, (rs, rowNum) -> {
            Room room = new Room();
            room.setIdRoom(rs.getInt("id_room"));
            room.setIdMaze(rs.getInt("id_maze"));
            room.setHaveCoin(rs.getBoolean("have_coin"));
            return room;
        });
    }
}
