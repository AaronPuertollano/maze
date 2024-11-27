package com.aaron_maze.maze.dao;

import com.aaron_maze.maze.model.Door;
import com.aaron_maze.maze.model.POSITION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoorDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Door> getDoorsByRoomId(int mazeId) {
        String sql = """
                SELECT d.*
                FROM Door d
                JOIN Room r ON d.id_room = r.id_room
                WHERE r.id_maze = ?""";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Door door = new Door();
            door.setIdDoor(rs.getInt("id_door"));
            door.setIdRoom(rs.getInt("id_room"));
            door.setIdNextRoom(rs.getInt("id_next_room"));
            door.setIdOpener(rs.getInt("id_opener"));
            door.setStateDoor(rs.getBoolean("state_door"));
            door.setPosition(POSITION.valueOf(rs.getString("position")));
            return door;
        }, mazeId);
    }
}