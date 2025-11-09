package com.example.aplicatie_pentru_sala_de_fitness;

import android.util.Pair;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Gym {
    private String name;
    private String adress;

    private String city;
    private String contact;

    private Map<String, Pair<String, String>> schedule;

    private List<String> facilities;

    private int imageResId;


    private String requiredMembershipType;

    public Gym(String name, String adress, String city, Map<String, Pair<String, String>> schedule, String contact, List<String> facilities, int imageResId, String requiredMembershipType) {
        this.name = name;
        this.adress = adress;
        this.city = city;
        this.schedule = schedule;
        this.contact = contact;
        this.facilities = facilities;
        this.imageResId = imageResId;
        this.requiredMembershipType = requiredMembershipType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Map<String, Pair<String, String>> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<String, Pair<String, String>> schedule) {
        this.schedule = schedule;
    }

    public List<String> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<String> facilities) {
        this.facilities = facilities;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getRequiredMembershipType() {
        return requiredMembershipType;
    }

    public void setRequiredMembershipType(String requiredMembershipType) {
        this.requiredMembershipType = requiredMembershipType;
    }

    @Override
    public String toString() {
        return "Gym{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", contact='" + contact + '\'' +
                ", schedule=" + schedule +
                ", facilities=" + facilities +
                ", imageResId=" + imageResId +
                ", requiredMembershipType='" + requiredMembershipType + '\'' +
                '}';
    }
}
