package com.teama.walkinclinic;

public class Appointment {



    String hourInterval;
    String quarterInterval;
    Clinic clinicForAppointment;

    public Appointment(String hourInterval, String quarterInterval, Clinic clinicForAppointment){
        this.hourInterval= hourInterval;
        this.quarterInterval = quarterInterval;
        this.clinicForAppointment = clinicForAppointment;
    }
    public String getHourInterval() {
        return hourInterval;
    }

    public void setHourInterval(String hourInterval) {
        this.hourInterval = hourInterval;
    }

    public String getQuarterInterval() {
        return quarterInterval;
    }

    public void setQuarterInterval(String quarterInterval) {
        this.quarterInterval = quarterInterval;
    }

    public Clinic getClinicForAppointment() {
        return clinicForAppointment;
    }

    public void setClinicForAppointment(Clinic clinicForAppointment) {
        this.clinicForAppointment = clinicForAppointment;
    }

    @Override
    public String toString() {
        return "Appointment will be at " + this.hourInterval + ":" + this.quarterInterval;
    }
}
