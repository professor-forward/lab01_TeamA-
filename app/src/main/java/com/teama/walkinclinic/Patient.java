package com.teama.walkinclinic;

public class Patient extends User
{


    public Patient(String id, String firstName, String lastName, String emailAddress, String password)
    {
        super(id,firstName,lastName,emailAddress,password);
    }
    public Patient(){}
    public String getFirstName()
    {
        return super.getFirstName();
    }

    public String getLastName() { return super.getLastName(); }

    public String getEmailAddress()
    {
        return super.getEmailAddress();
    }

    public String getPassword()
    {
        return super.getPassword();
    }

    public String getId()
    {
        return super.getId();
    }



}
