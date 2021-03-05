package com.company;

import java.awt.*;

public class Planet {

    public String name;

    public double mass;
    public double xPos;
    public double yPos;
    public double xAccel = 0;
    public double yAccel = 0;
    public double xVel;
    public double yVel;

    public int size;

    public Color color;

    public Planet(String name, double mass, int size, double xPos, double yPos, Color color, double yVel, double xVel){
        this.name = name;
        this.mass = mass;
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
        this.xVel = xVel;
        this.yVel = yVel;
        this.size = size;
    }

    public void move(){
        xVel += xAccel;
        yVel += yAccel;
        xPos += xVel;
        yPos += yVel;
    }
}
