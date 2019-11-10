package com.teama.walkinclinic;

import static org.junit.Assert.*;
import org.junit.Test;

public class ServiceScreenTest2 {
    @Test
    public void checkName(){
        String name = "priest";
        String pay = "0";
        String role = "mediator";
        Service service2 = new Service(name, pay,role);
        assertEquals("Check the name of the service", "priest", service2.getName());
    }
    @Test
    public void checkPay(){
        String name = "priest";
        String pay = "0";
        String role = "mediator";
        Service service2 = new Service(name, pay,role);
        assertEquals("Check the pay of the service", "0", service2.getPay());
    }
    @Test
    public void checkRole(){
        String name = "priest";
        String pay = "0";
        String role = "mediator";
        Service service2 = new Service(name, pay,role);
        assertEquals("Check the pay of the service", "mediator", service2.getRole());
    }

}
