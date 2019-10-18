package com.teama.walkinclinic;

public class Employee extends User
{
    public Employee(String id, String firstName, String lastName, String emailAddress, String password)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Employee(){}
}
