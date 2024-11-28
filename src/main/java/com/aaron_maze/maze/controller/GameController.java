package com.aaron_maze.maze.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {
    //EL USUARIO TIENE QUE COMUNICAR CON EL SERVIDOR CADA VEZ QUE INTERACTUE CON UNA PUERTA U OBJETO, EL OBJETO SE DEBE COMPROBAR
    //EN EL SERVIDOR ANTES DE OBTENERLO, ES MEDIANTE JSON!
    //PARA GUARDAR EL TIEMPO DE PARTIDO LO CORRECTO SERIA GUARDAR LA HORA DE INICIO DE PARTIDA Y LA HORA DE FINAL
    //Y DESPUES HACER LOS CALCULOS
    @Autowired
    private MazeService mazeService;
    @Autowired
    private RoomService roomService;

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

            if (selectedMazeId == 1) {
                currentRoomId = 1;
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Invalid maze ID"));
            }

        }

        Room room = roomService.getRoomById(currentRoomId);
        List<Door> doors = roomService.getDoorsByRoomId(currentRoomId);

        Map<String, Object> response = new HashMap<>();
        response.put("room", room);
        response.put("doors", doors);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/open")
    public ResponseEntity<Map<String, Object>> openDoor(

            @RequestParam("idDoor") Integer idDoor, HttpSession session) {

        String user = (String) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        // Busca la puerta por ID
        Door door = roomService.getDoorById(idDoor);
        if (door == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "La puerta no existe"));
        }

        boolean canOpen = door.isStateDoor();

        Map<String, Object> response = new HashMap<>();
        response.put("canOpen", canOpen);

        if (canOpen) {
            session.setAttribute("currentRoom", door.getIdNextRoom());
            response.put("nextRoom", door.getIdNextRoom());
        } else {
            response.put("message", "La puerta está cerrada");
        }

        return ResponseEntity.ok(response);
    }


}
