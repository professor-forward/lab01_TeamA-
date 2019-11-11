package com.teama.walkinclinic;

public class Shift{

    String month;
    String date;
    String hours;

    public Shift(String month, String date, String hours){
        this.month = month;
        this.date = date;
        this.hours = hours;
    }

    public Shift(){}

    public String toString(){
        return this.month + ":" + this.date + ":" + this.hours;
    }
}