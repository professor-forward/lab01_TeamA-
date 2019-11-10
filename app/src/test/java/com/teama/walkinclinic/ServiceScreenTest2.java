package com.teama.walkinclinic;

import static org.junit.Assert.*;
import org.junit.Test;

public class ServiceScreenTest2 {
    @Test
    public void checkName(){
        String name = "priest";
        String pay = "0";
        Service service2 = new Service(name, pay);
        assertEquals("Check the name of the service", "priest", service2.getName());
    }
    @Test
    public void checkPay(){
        String name = "priest";
        String pay = "0";
        Service service2 = new Service(name, pay);
        assertEquals("Check the pay of the service", "0", service2.getPay());
    }

}
