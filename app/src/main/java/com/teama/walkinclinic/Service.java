package com.teama.walkinclinic;

public class Service {
    private String iD;
    private String name;
    private String pay;

    public Service(String iD, String name, String pay){
        this.iD = iD.trim();
        this.name = name.trim();
        this.pay = pay.trim();
    }

    public String getID(){
        return this.iD;
    }
    public String getName(){
        return this.name;
    }
    public String getPay(){
        return this.pay;
    }
    public void setID(String iD){
        this.iD = iD.trim();
    }
    public void setName(String name){
        this.name = name.trim();
    }
    public void setPay(String pay){
        this.pay = pay.trim();
    }
}
