package com.teama.walkinclinic;

public class Employee extends User
{
    String[] shift;
    int shiftnum = 0;

    public Employee(String firstName, String lastName, String emailAddress){
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
}
    public Employee(){}

    public String getShift(){
        String allShifts = " ";
        if(shift.length>0){
            for(int x = 0; x<shift.length;x++){
                allShifts = shift[x] + " ";
            }
            return allShifts;
        }
        else{
            return "This employee currently has no shfits";
        }
    }

    public void setShift(Shift shift){
        this.shift[shiftnum++] = shift.toString();
    }





}
