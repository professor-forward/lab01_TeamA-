package com.teama.walkinclinic;

public class Clinic {

    String clinicName;
    String clinicAddress;
    String clinicPhoneNumber;
    String clinicOpenHours;
    boolean clinicCredit;
    boolean clinicDebit;
    boolean clinicBitcoin;


    public Clinic(String clinicName, String clinicAddress, String clinicPhoneNumber, String clinicOpenHours, boolean clinicCredit, boolean clinicDebit, boolean clinicBitcoin) {
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.clinicPhoneNumber = clinicPhoneNumber;
        this.clinicOpenHours = clinicOpenHours;

        this.clinicCredit = clinicCredit;
        this.clinicDebit = clinicDebit;
        this.clinicBitcoin = clinicBitcoin;

    }

    public Clinic(){}

    public String getClinicName() {
        return clinicName;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public String getPhoneNumber() {
        return clinicPhoneNumber;
    }

    public String getClinicOpenHours() {
        return clinicOpenHours;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.clinicPhoneNumber = phoneNumber;
    }

    public void setClinicOpenHours(String clinicOpenHours) {
        this.clinicOpenHours = clinicOpenHours;
    }

    public String toString(){
        return this.clinicName + " located at " + this.clinicAddress;
    }

}
