package com.aaron_maze.maze.services;

import com.aaron_maze.maze.dao.RoomDAO;
import com.aaron_maze.maze.model.Maze;
import com.aaron_maze.maze.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    private RoomDAO roomDAO;

    public Room getRoomById(int idRoom) {
        return roomDAO.getRoomById(idRoom);
    }
}
