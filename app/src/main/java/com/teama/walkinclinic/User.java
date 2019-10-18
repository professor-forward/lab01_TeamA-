package com.teama.walkinclinic;

public abstract class User
{
    protected String firstName;
    protected String lastName;
    protected String emailAddress;
    protected String password;
    protected String id;

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
}
