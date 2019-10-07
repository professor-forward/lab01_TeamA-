package com.teama.walkinclinic;

public class Patient extends User
{
    public Patient(String firstName, String lastName, String emailAddress, String password)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }
}
