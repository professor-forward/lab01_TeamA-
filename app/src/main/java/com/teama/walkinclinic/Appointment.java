package com.teama.walkinclinic;

public class Appointment {



    String hourInterval;
    String quarterInterval;
    Clinic clinicForAppointment;
    Patient patientForAppointment;

    public Appointment(String hourInterval, String quarterInterval, Clinic clinicForAppointment, Patient patientForAppointment){
        this.hourInterval= hourInterval;
        this.quarterInterval = quarterInterval;
        this.clinicForAppointment = clinicForAppointment;
        this.patientForAppointment = patientForAppointment;
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



    @Override
    public String toString() {
        return "Appointment will be at " + this.hourInterval + ":" + this.quarterInterval;
    }
}
