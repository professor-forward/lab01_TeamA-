package com.teama.walkinclinic;

public class Service {

    private String name;
    private String pay;
    private String role;

    public Service(String name, String pay, String role){

        this.name = name.trim();
        this.role = role.trim();
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
    public String getRole(){return this.role; }

    public void setName(String name){
        this.name = name.trim();
    }
    public void setPay(String pay){
        this.pay = pay.trim();
    }
    public void setRole(String role){
        this.role = role.trim();
    }

    public String toString(){
        return this.name;
    }

    // allows for the comparison on two services
    public boolean equals(Service other)
    {
        if(other == null)
        {
            return false;
        }

        if(name == null || pay == null || role == null)
        {
            return false;
        }

        if(other.getName().equals(this.name) && other.getPay().equals(this.pay) && other.getRole().equals(this.role))
        {
            return true;
        }
        else
            {
                return false;
            }
    }
}
