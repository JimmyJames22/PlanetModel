package com.company;

import java.awt.*;

public class Planet {

    private int mass;
    private int pos;
    private Color color;
    private int vel;

    public Planet(int mass, int pos, Color color, int vel){
        this.mass = mass;
        this.pos = pos;
        this.color = color;
        this.vel = vel;
    }

    public int getPos(){
        return pos;
    }

    public int getVel(){
        return vel;
    }

    public Color getColor(){
        return color;
    }
}
