package com.teama.walkinclinic;

public class Clinic {

    String clinicName;
    String clinicAddress;
    String clinicPhoneNumber;
    String clinicOperatingHours;
    boolean clinicCredit;
    boolean clinicDebit;
    boolean clinicBitcoin;
    int openingHours;
    int closingHours;


    public Clinic(String clinicName, String clinicAddress, String clinicPhoneNumber, String clinicOperatingHours, boolean clinicCredit, boolean clinicDebit, boolean clinicBitcoin) {
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.clinicPhoneNumber = clinicPhoneNumber;
        this.clinicOperatingHours = clinicOperatingHours;

        this.clinicCredit = clinicCredit;
        this.clinicDebit = clinicDebit;
        this.clinicBitcoin = clinicBitcoin;

        if (clinicOperatingHours.equals("Regular Clinic Hours: 8AM - 8PM")) {
            this.openingHours = 8;
            this.closingHours = 20;
        }
        else if(clinicOperatingHours.equals("Specialty Clinic Hours: 10AM - 2PM")){
            this.openingHours = 10;
            this.closingHours = 14;
        }
        else{
            this.openingHours = 24;
            this.closingHours = 24;
        }


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

    public int getOpeningHours(){
        return openingHours;
    }

    public int getClosingHours(){
        return closingHours;
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

    public void setOpeningHours(int openingHours){
        this.openingHours = openingHours;
    }

    public void setClosingHours(int closingHours){
        this.closingHours = closingHours;
    }

    public String toString(){
        return this.clinicName + " located at " + this.clinicAddress;
    }

}
