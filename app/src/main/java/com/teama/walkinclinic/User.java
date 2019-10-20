package com.teama.walkinclinic;

public abstract class User
{
    protected String firstName;
    protected String lastName;
    protected String emailAddress;
    protected String iD;

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

    public String getiD(){return iD;}


    public void setFirstName(String firstName) {this.firstName = firstName;}

    public void setLastName(String lastName) { this.lastName = lastName;}

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public void setiD(String iD){this.iD = iD;}
}
