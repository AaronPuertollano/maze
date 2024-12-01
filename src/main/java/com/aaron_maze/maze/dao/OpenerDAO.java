package com.aaron_maze.maze.dao;

import com.aaron_maze.maze.model.Opener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OpenerDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Opener> getOpenersByRoomId(int roomId) {
        String sql = """
                SELECT d.*
                FROM Opener d
                WHERE d.id_room = ?""";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Opener opener = new Opener();
            opener.setIdOpener(rs.getInt("id_opener"));
            opener.setIdRoom(rs.getInt("id_room"));
            opener.setCost(rs.getInt("cost"));
            return opener;
        }, roomId);
    }
}
