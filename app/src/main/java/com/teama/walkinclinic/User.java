package com.teama.walkinclinic;

public abstract class User
{
    protected String firstName;
    protected String lastName;
    protected String emailAddress;


    public User(String firstName, String lastName, String emailAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }
    public User(){}

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmailAddress()
    {
        return emailAddress;
    }



    public void setFirstName(String firstName) {this.firstName = firstName;}

    public void setLastName(String lastName) { this.lastName = lastName;}

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String toString()
    {
        return emailAddress + ", " + firstName + " " + lastName;
    }

}
