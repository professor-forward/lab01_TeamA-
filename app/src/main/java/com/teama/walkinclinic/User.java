package com.teama.walkinclinic;

public class User
{
    protected String firstName;
    protected String lastName;
    protected String emailAddress;
    protected String password;
    protected String id;

    public User(String id, String firstName, String lastName, String emailAddress, String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
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

    public String getPassword()
    {
        return password;
    }

    public String getId()
    {
        return id;
    }

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public void setLastName(String lastName) { this.lastName = lastName;}

    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) { this.password = password; }

    public void setId() { this.id = id; }


}
