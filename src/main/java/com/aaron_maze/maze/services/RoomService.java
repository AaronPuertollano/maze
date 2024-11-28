package com.aaron_maze.maze.services;

import com.aaron_maze.maze.dao.DoorDAO;
import com.aaron_maze.maze.dao.RoomDAO;
import com.aaron_maze.maze.model.Door;
import com.aaron_maze.maze.model.Maze;
import com.aaron_maze.maze.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomDAO roomDAO;

    @Autowired
    DoorDAO doorDAO;

    public Room getRoomById(int idRoom) {
        return roomDAO.getRoomById(idRoom);
    }

    public List<Door> getDoorsByRoomId(int roomId) {
        return doorDAO.getDoorsByRoomId(roomId);
    }

    public Door getDoorById(int idDoor){
        return doorDAO.getDoorById(idDoor);
    }
}
