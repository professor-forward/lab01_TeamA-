package com.teama.walkinclinic;

public class Shift{

    String month;
    String date;
    String hours;
    String clinicName;

    public Shift(String month, String date, String hours, String clinicName){
        this.month = month;
        this.date = date;
        this.hours = hours;
        this.clinicName = clinicName;
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

    public String getClinicName() {
        return clinicName;
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

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }





    public String toString(){
        return this.clinicName +":"+ this.date +":"+ this.hours +":"+ this.month;
    }
}