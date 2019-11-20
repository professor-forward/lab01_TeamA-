package com.teama.walkinclinic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClinicTest {
    @Test
    public void checkClinicName(){
        String clinicName = "A-";
        String clinicAddress = "K1N 6N5";
        String clinicPhoneNumber = "(613) 562-5800 ext. 6568";
        String clinicOperatingHours = "Working Emergency Clinic Hours: 2am to 8am";
        boolean clinicCredit = true;
        boolean clinicDebit = true;
        boolean clinicBitcoin = true;
        Clinic clinic = new Clinic(clinicName, clinicAddress, clinicPhoneNumber,clinicOperatingHours, clinicCredit, clinicDebit, clinicBitcoin);
        assertEquals("Check name of clinic", "A-", clinic.getClinicName());
    }
    @Test
    public void checkClinicAddress(){
        String clinicName = "A-";
        String clinicAddress = "K1N 6N5";
        String clinicPhoneNumber = "(613) 562-5800 ext. 6568";
        String clinicOperatingHours = "Working Emergency Clinic Hours: 2am to 8am";
        boolean clinicCredit = true;
        boolean clinicDebit = true;
        boolean clinicBitcoin = true;
        Clinic clinic = new Clinic(clinicName, clinicAddress, clinicPhoneNumber,clinicOperatingHours, clinicCredit, clinicDebit, clinicBitcoin);
        assertEquals("Check address of clinic", "K1N 6N5", clinic.getClinicAddress());
    }
    @Test
    public void checkPhoneNumber(){
        String clinicName = "A-";
        String clinicAddress = "K1N 6N5";
        String clinicPhoneNumber = "(613) 562-5800 ext. 6568";
        String clinicOperatingHours = "Working Emergency Clinic Hours: 2am to 8am";
        boolean clinicCredit = true;
        boolean clinicDebit = true;
        boolean clinicBitcoin = true;
        Clinic clinic = new Clinic(clinicName, clinicAddress, clinicPhoneNumber,clinicOperatingHours, clinicCredit, clinicDebit, clinicBitcoin);
        assertEquals("Check operating hours of clinic", "(613) 562-5800 ext. 6568", clinic.getPhoneNumber());
    }
    @Test
    public void checkOperatingHours(){
        String clinicName = "A-";
        String clinicAddress = "K1N 6N5";
        String clinicPhoneNumber = "(613) 562-5800 ext. 6568";
        String clinicOperatingHours = "Working Emergency Clinic Hours: 2am to 8am";
        boolean clinicCredit = true;
        boolean clinicDebit = true;
        boolean clinicBitcoin = true;
        Clinic clinic = new Clinic(clinicName, clinicAddress, clinicPhoneNumber,clinicOperatingHours, clinicCredit, clinicDebit, clinicBitcoin);
        assertEquals("Check address of clinic", "Working Emergency Clinic Hours: 2am to 8am", clinic.getClinicOperatingHours());
    }

}
