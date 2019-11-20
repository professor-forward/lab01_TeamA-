package com.teama.walkinclinic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShiftTest {
    @Test
    public void checkMonth(){
        String month = "november";
        String date = "15";
        String hours = "8AM - 9PM";
        String clinicName = "A-";
        Shift shift = new Shift(month,date,hours,clinicName);
        assertEquals("Check month of shift", "november", shift.getMonth());

    }
    @Test
    public void checkDate(){
        String month = "november";
        String date = "15";
        String hours = "8AM - 9PM";
        String clinicName = "A-";
        Shift shift = new Shift(month,date,hours,clinicName);
        assertEquals("Check date of shift", "15", shift.getDate());
    }
    @Test
    public void checkHours(){
        String month = "november";
        String date = "15";
        String hours = "8AM - 9PM";
        String clinicName = "A-";
        Shift shift = new Shift(month,date,hours,clinicName);
        assertEquals("Check hours of shift", "8AM - 9PM", shift.getHours());
    }
    @Test
    public void checkClinicName(){
        String month = "november";
        String date = "15";
        String hours = "8AM - 9PM";
        String clinicName = "A-";
        Shift shift = new Shift(month,date,hours,clinicName);
        assertEquals("Check name of Clinic", "A-", shift.getClinicName());
    }




}
