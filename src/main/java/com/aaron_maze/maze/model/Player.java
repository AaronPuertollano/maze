package com.aaron_maze.maze.model;

public class Player {
    int idPlayer;
    private String name;
    private String password;

    // Constructors
    public Player() {}

    public Player(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}