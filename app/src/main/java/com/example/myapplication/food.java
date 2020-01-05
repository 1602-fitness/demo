package com.example.myapplication;

public class food {
    private String name;
    private int energy;
    public food(String n,int e){
        this.name = n;
        this.energy = e;
    }
    public String getName(){
        return name;
    }
    public int getEnergy()
    {
        return energy;
    }
}
