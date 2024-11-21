package com.aaron_maze.maze.model;
enum POSITION{
    NORTH, SOUTH, EAST, WEST
}
public class Door {
    int idDoor;
    int idRoom;
    int inNextRoom;
    int idOpener;
    boolean stateDoor;
    POSITION position;
}
