package com.teama.walkinclinic;

import static org.junit.Assert.*;
import org.junit.Test;

public class ServiceScreenTest {
    @Test
    public void checkName(){
        String name = "magus";
        String pay = "1000";
        String role = "master";
        Service service1 = new Service(name, pay,role);
        assertEquals("Check the name of the service", "magus", service1.getName());
    }
    @Test
    public void checkPay(){
        String name = "magus";
        String pay = "1000";
        String role = "master";
        Service service1 = new Service(name, pay,role);
        assertEquals("Check the pay of the service", "1000", service1.getPay());
    }
    @Test
    public void checkRole(){
        String name = "magus";
        String pay = "1000";
        String role = "master";
        Service service1 = new Service(name, pay,role);
        assertEquals("Check the pay of the service", "master", service1.getRole());
    }

}
