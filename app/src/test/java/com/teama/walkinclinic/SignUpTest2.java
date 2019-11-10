package com.teama.walkinclinic;

import static org.junit.Assert.*;
import org.junit.Test;

public class SignUpTest2 {

    @Test
    public void checkFirstName(){
        String firstName = "kirei";
        String lastName = "kotomine";
        String email = "holygrail@gmail.com";
        String password = "kyrieeleison";
        User falsepriest = new Employee(firstName, lastName, email);
        assertEquals("Check firstname of the user", "kirei", falsepriest.getFirstName());


    }
    @Test
    public void checkLastName(){
        String firstName = "kirei";
        String lastName = "kotomine";
        String email = "holygrail@gmail.com";
        String password = "kyrieeleison";
        User falsepriest = new Employee(firstName, lastName, email);
        assertEquals("Check firstname of the user", "kotomine", falsepriest.getLastName());

    }
    @Test
    public void checkEmail(){
        String firstName = "kirei";
        String lastName = "kotomine";
        String email = "holygrail@gmail.com";
        String password = "kyrieeleison";
        User falsepriest = new Employee(firstName, lastName, email);
        assertEquals("Check email of the user", "holygrail@gmail.com",falsepriest.getEmailAddress());
    }
}
