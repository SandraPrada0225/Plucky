package com.example.plucky.GameObjects;

public class Vector2DPlucky {
    private double x,y;

    public Vector2DPlucky(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector2DPlucky(){
        x=0;
        y=0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
