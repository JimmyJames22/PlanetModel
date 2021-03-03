package com.company;

import java.awt.*;

public class Planet {

    public String name;

    public int mass;
    public int xPos;
    public int yPos;
    public int xAccel = 0;
    public int yAccel = 0;
    public int xVel;
    public int yVel;

    public Color color;

    public Planet(String name, int mass, int xPos, int yPos, Color color, int yVel, int xVel){
        this.name = name;
        this.mass = mass;
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
        this.xVel = xVel;
        this.xVel = yVel;
    }

    public void move(){
        xVel += xAccel;
        yVel += yAccel;
        xPos += xVel;
        yPos += yVel;
        System.out.println(name + " " + xPos + " " + yPos);
    }
}
