package com.teama.walkinclinic;

public class Employee extends User
{
    public Employee(String firstName, String lastName, String emailAddress, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }
}
