package com.aaron_maze.maze.controller;

import com.aaron_maze.maze.dao.DoorDAO;
import com.aaron_maze.maze.dao.PlayerDAO;
import com.aaron_maze.maze.model.Door;
import com.aaron_maze.maze.model.Player;
import com.aaron_maze.maze.model.Room;
import com.aaron_maze.maze.services.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/game")
public class GameApiController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private PlayerDAO playerDAO;

    @Autowired
    private DoorDAO doorDAO;

    @GetMapping("/room")
    public ResponseEntity<Map<String, Object>> getCurrentRoom(HttpSession session) {
        String user = (String) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Integer currentRoomId = (Integer) session.getAttribute("currentRoom");
        if (currentRoomId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Room room = roomService.getRoomById(currentRoomId);
        List<Door> doors = doorDAO.getDoorsByRoomId(currentRoomId);

        Map<String, Object> response = new HashMap<>();
        response.put("room", room);
        response.put("doors", doors);
        return ResponseEntity.ok(response);
    }

    private List<Map<String, Object>> getDoorsForRoom(int roomId) {
        // Obtener puertas de la habitación actual
        List<Door> doors = doorDAO.getDoorsByRoomId(roomId); // Ajusta este método si es necesario
        List<Map<String, Object>> doorList = new ArrayList<>();

        for (Door door : doors) {
            Map<String, Object> doorData = new HashMap<>();
            doorData.put("idDoor", door.getIdDoor());
            doorData.put("position", door.getPosition());
            doorData.put("stateDoor", door.isStateDoor());
            doorList.add(doorData);
        }

        return doorList;
    }
}
