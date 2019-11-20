package com.teama.walkinclinic;

public class Clinic {

    String clinicName;
    String clinicAddress;
    String clinicPhoneNumber;
    String clinicOperatingHours;



    boolean clinicCredit;
    boolean clinicDebit;
    boolean clinicBitcoin;


    public Clinic(String clinicName, String clinicAddress, String clinicPhoneNumber, String clinicOperatingHours, boolean clinicCredit, boolean clinicDebit, boolean clinicBitcoin) {
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.clinicPhoneNumber = clinicPhoneNumber;
        this.clinicOperatingHours = clinicOperatingHours;

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

    public String getClinicOperatingHours() {
        return clinicOperatingHours;
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

    public void setClinicOperatingHours(String clinicOperatingHours) {
        this.clinicOperatingHours = clinicOperatingHours;
    }
    public boolean isClinicCredit() {
        return clinicCredit;
    }

    public void setClinicCredit(boolean clinicCredit) {
        this.clinicCredit = clinicCredit;
    }

    public boolean isClinicDebit() {
        return clinicDebit;
    }

    public void setClinicDebit(boolean clinicDebit) {
        this.clinicDebit = clinicDebit;
    }

    public boolean isClinicBitcoin() {
        return clinicBitcoin;
    }

    public void setClinicBitcoin(boolean clinicBitcoin) {
        this.clinicBitcoin = clinicBitcoin;
    }

    public int clinicMinHours(){
        if (clinicOperatingHours.equals("Regular Clinic Hours: 8AM - 8PM")) {
            return 8;
        }
        else if(clinicOperatingHours.equals("Specialty Clinic Hours: 10AM - 2PM")){
            return 10;
        }
        else{return 0;}
    }
    public int clinicMaxHours(){
        if (clinicOperatingHours.equals("Regular Clinic Hours: 8AM - 8PM")) {
            return 20;
        }
        else if(clinicOperatingHours.equals("Specialty Clinic Hours: 10AM - 2PM")){
            return 14;
        }
        else{return 24;}
    }



    public String toString(){
        return this.clinicName + " located at " + this.clinicAddress;
    }

}
