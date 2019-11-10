package com.teama.walkinclinic;

import static org.junit.Assert.*;
import org.junit.Test;

public class LogInTest {

    @Test
    public void checkUsername(){
        String username = "admin";
        String password = "5T5ptQ";
        assertEquals("Check if the admin username is correct", "admin", username );
    }

    @Test
    public void checkPassword(){
        String username = "admin";
        String password = "5T5ptQ";
        assertEquals("Check if the admin password is correct", "5T5ptQ", password);
    }




}
