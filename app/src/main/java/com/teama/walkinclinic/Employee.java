package com.teama.walkinclinic;

import java.util.ArrayList;
public class Employee extends User
{
    ArrayList<Shift> shiftList = new ArrayList<Shift>();
    int shiftnum = 0;

    public Employee(String firstName, String lastName, String emailAddress){
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
}
    public Employee(){}

    public void addShift(Shift shift){
        this.shiftList.add(shift);
    }

}
