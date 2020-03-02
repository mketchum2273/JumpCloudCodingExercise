package com.company;

public class Action {
    private String name;
    private int time;

    public Action(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }
}
