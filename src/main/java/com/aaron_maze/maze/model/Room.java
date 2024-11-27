package com.aaron_maze.maze.model;

import java.lang.reflect.Array;
import java.util.List;

public class Room {

    int idRoom;
    int idMaze;
    boolean haveCoin;

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdMaze() {
        return idMaze;
    }

    public void setIdMaze(int idMaze) {
        this.idMaze = idMaze;
    }

    public boolean isHaveCoin() {
        return haveCoin;
    }

    public void setHaveCoin(boolean haveCoin) {
        this.haveCoin = haveCoin;
    }
}
