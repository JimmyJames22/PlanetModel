package com.company;

import java.awt.*;
import java.util.ArrayList;

public class Planet {

    public String name;

    public double mass;
    public double xPos;
    public double yPos;
    public double xAccel = 0;
    public double yAccel = 0;
    public double force;
    public double xVel;
    public double yVel;
    public double rad;

    public int size;

    public Color color;

    public ArrayList<Vector> vectors = new ArrayList<Vector>();

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
        setVectors();
        xVel += xAccel;
        yVel += yAccel;
        xPos += xVel;
        yPos += yVel;
    }

    public void setVectors(){
        xAccel = 0;
        yAccel = 0;
        rad = 0;
        force = 0;
        for(int i=0; i<vectors.size(); i++){
            xAccel += vectors.get(i).xForce()/mass;
            yAccel += vectors.get(i).yForce()/mass;
        }
        rad = Math.atan(yAccel/xAccel);
        if(xAccel<0){
            rad += Math.PI;
        }
        vectors.clear();
    }
}
