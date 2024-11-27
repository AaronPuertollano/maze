package com.aaron_maze.maze.model;

import java.util.List;

public class Maze {
    String name;
    int idMaze;
    String description;
    int id_start_room;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdMaze() {
        return idMaze;
    }

    public void setIdMaze(int idMaze) {
        this.idMaze = idMaze;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_start_room() {
        return id_start_room;
    }

    public void setStartRoomId(int id_start_room) {
        this.id_start_room = id_start_room;
    }
}
