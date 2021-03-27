package com.company;

import java.awt.*;
import java.util.ArrayList;

public class MovingObj {

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

    public int sizeX;
    public int sizeY;

    public Color color;

    public ArrayList<Vector> vectors = new ArrayList<Vector>();

    public Image pic;

    public MovingObj (String name, double mass, int sizeX, int sizeY, double xPos, double yPos, Color color, double yVel, double xVel, Image pic) {
        this.name = name;
        this.mass = mass;
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.color = color;
        this.pic = pic;
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

    public void move(){
        setVectors();
        xVel += xAccel;
        yVel += yAccel;
        xPos += xVel;
        yPos += yVel;
    }

    public double OffsetX(MovingObj focusedObj){
        return (focusedObj.xPos - xPos) * -1;
    }

    public double OffsetY(MovingObj focusedObj){
        return (focusedObj.yPos - yPos) * -1;
    }
}
