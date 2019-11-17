package com.teama.walkinclinic;

public class Clinic {

    static String clinicName;
    static String clinicAddress;
    static String phoneNumber;
    static String clinicOpenHours;

    public Clinic(){}

    public static String getClinicName() {
        return clinicName;
    }

    public static String getClinicAddress() {
        return clinicAddress;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static String getClinicOpenHours() {
        return clinicOpenHours;
    }

    public static void setClinicName(String clinicName) {
        Clinic.clinicName = clinicName;
    }

    public static void setClinicAddress(String clinicAddress) {
        Clinic.clinicAddress = clinicAddress;
    }

    public static void setPhoneNumber(String phoneNumber) {
        Clinic.phoneNumber = phoneNumber;
    }

    public static void setClinicOpenHours(String clinicOpenHours) {
        Clinic.clinicOpenHours = clinicOpenHours;
    }


}
