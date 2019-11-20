package com.teama.walkinclinic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShiftTest {
    @Test
    public void checkFirstName(){
        String firstName = "shirou";
        String lastName = "emiya";
        String email = "kanshoubakuya@gmail.com";
        String password = "triplelinkedcranewings";
        User allyofjustice = new Patient(firstName, lastName, email);
        assertEquals("Check firstname of the user", "shirou", allyofjustice.getFirstName());


    }
    @Test
    public void checkLastName(){
        String firstName = "shirou";
        String lastName = "emiya";
        String email = "kanshoubakuya@gmail.com";
        String password = "triplelinkedcranewings";
        User allyofjustice = new Patient(firstName, lastName, email);
        assertEquals("Check firstname of the user", "emiya", allyofjustice.getLastName());

    }
    @Test
    public void checkEmail(){
        String firstName = "shirou";
        String lastName = "emiya";
        String email = "kanshoubakuya@gmail.com";
        String password = "triplelinkedcranewings";
        User allyofjustice = new Patient(firstName, lastName, email);
        assertEquals("Check email of the user", "kanshoubakuya@gmail.com",allyofjustice.getEmailAddress());
    }
}
