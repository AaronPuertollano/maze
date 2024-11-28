package com.aaron_maze.maze.services;

import com.aaron_maze.maze.dao.DoorDAO;
import com.aaron_maze.maze.dao.PlayerDAO;
import com.aaron_maze.maze.model.Door;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoorService {

    @Autowired
    DoorDAO doorDAO;
//puede pasarse a roomService para globalizarlo mas
    public List<Door> getDoorsByRoomId(int mazeId) {
        return doorDAO.getDoorsByRoomId(mazeId);
    }

}


