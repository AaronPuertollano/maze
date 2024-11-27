package com.aaron_maze.maze.model;

public class Door {
    int idDoor;
    int idRoom;
    int idNextRoom;
    int idOpener;
    boolean stateDoor;
    POSITION position;

    public int getIdDoor() {
        return idDoor;
    }

    public void setIdDoor(int idDoor) {
        this.idDoor = idDoor;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdNextRoom() {
        return idNextRoom;
    }

    public void setIdNextRoom(int idNextRoom) {
        this.idNextRoom = idNextRoom;
    }

    public int getIdOpener() {
        return idOpener;
    }

    public void setIdOpener(int idOpener) {
        this.idOpener = idOpener;
    }

    public boolean isStateDoor() {
        return stateDoor;
    }

    public void setStateDoor(boolean stateDoor) {
        this.stateDoor = stateDoor;
    }

    public POSITION getPosition() {
        return position;
    }

    public void setPosition(POSITION position) {
        this.position = position;
    }
}
