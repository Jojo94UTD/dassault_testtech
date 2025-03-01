package com.testDassault.droneDassault.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "drones")
public class Drone {
    @Id
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private int batteryLevel;
    private String status;

    // Constructeurs
    public Drone() {
    }

    public Drone(String name, double latitude, double longitude, int batteryLevel, String status) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.batteryLevel = batteryLevel;
        this.status = status;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Méthodes pour gérer les drones
    public void demarrerMission() {
        this.status = "En mission";
        System.out.println("Drone " + name + " a démarré une mission.");
    }

    public void atterrir() {
        this.status = "Au sol";
        System.out.println("Drone " + name + " a atterri.");
    }

    public void changerPosition(double nouvelleLatitude, double nouvelleLongitude) {
        this.latitude = nouvelleLatitude;
        this.longitude = nouvelleLongitude;
        System.out.println("Drone " + name + " a changé de position : (" + nouvelleLatitude + ", " + nouvelleLongitude + ")");
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", batteryLevel=" + batteryLevel +
                ", status='" + status + '\'' +
                '}';
    }
}