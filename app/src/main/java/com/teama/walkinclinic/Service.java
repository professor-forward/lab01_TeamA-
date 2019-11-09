package com.teama.walkinclinic;

public class Service {

    private String name;
    private String pay;

    public Service(String name, String pay){

        this.name = name.trim();
        this.pay = pay.trim();
    }
    public Service(){

    }


    public String getName(){
        return this.name;
    }
    public String getPay(){
        return this.pay;
    }

    public void setName(String name){
        this.name = name.trim();
    }
    public void setPay(String pay){
        this.pay = pay.trim();
    }
    public String toString(){
        return this.name + ":" + this.pay;
    }
}
