package com.aaron_maze.maze.controller;

import com.aaron_maze.maze.dao.DoorDAO;
import com.aaron_maze.maze.model.Door;
import com.aaron_maze.maze.model.Maze;
import com.aaron_maze.maze.model.Room;
import com.aaron_maze.maze.services.MazeService;
import com.aaron_maze.maze.services.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {
    //EL USUARIO TIENE QUE COMUNICAR CON EL SERVIDOR CADA VEZ QUE ITERACTUE CON UNA PUERTA U OBJETO, EL OBJETO SE DEBE COMPROBAR
    //EN EL SERVIDOR ANTES DE OBTENERLO, ES MEDIANTE JSON!
    @Autowired
    private MazeService mazeService;

    @Autowired
    private RoomService roomService;
    @Autowired
    private DoorDAO doorDAO;

    @GetMapping("/start")
    public String showStartPage(HttpSession session, Model model) {

        String user = (String) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("user", user);

        return "start";
    }

    @GetMapping("/game")
    public String startGame(HttpSession session, Model model) {

        String user = (String) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        // Obtiene el ID
        Integer selectedMazeId = (Integer) session.getAttribute("selectedMaze");
        if (selectedMazeId == null) {
            return "redirect:/menu";
        }

        model.addAttribute("user", user);
        model.addAttribute("selectedMaze", selectedMazeId);

        Maze selectedMaze = mazeService.getMazeById(selectedMazeId);
        model.addAttribute("mazeName", selectedMaze.getName());

        return "start";
    }

    @GetMapping("/room")
    public ResponseEntity<Map<String, Object>> getCurrentRoom(HttpSession session) {
        String user = (String) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Integer selectedMazeId = (Integer) session.getAttribute("selectedMaze");
        Integer currentRoomId = (Integer) session.getAttribute("currentRoom");

        if (currentRoomId == null) {
            //dependiendo del maze seleccionado el primer room sera uno u otro

            if (selectedMazeId == 1){
                currentRoomId = 1;
            }

        }

        Room room = roomService.getRoomById(currentRoomId);
        List<Door> doors = doorDAO.getDoorsByRoomId(currentRoomId);

        Map<String, Object> response = new HashMap<>();
        response.put("room", room);
        response.put("doors", doors);
        return ResponseEntity.ok(response);
    }


}
