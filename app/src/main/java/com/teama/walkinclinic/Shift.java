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

    public String getMonth() {
        return month;
    }

    public String getDate() {
        return date;
    }

    public String getHours() {
        return hours;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String toString(){
        return this.month + ":" + this.date + ":" + this.hours;
    }
}