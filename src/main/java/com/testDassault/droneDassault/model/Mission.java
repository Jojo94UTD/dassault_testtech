package com.testDassault.droneDassault.model;

public class Mission {
    private String id;
    private String type; // Ex: "Surveillance", "Livraison"
    private String trajectoire; // Vous pouvez utiliser une classe Trajectoire pour plus de précision
    private String statut; // Ex: "En cours", "Terminée", "Annulée"

    // Constructeurs, getters et setters
    public Mission() {}

    public Mission(String id, String type, String trajectoire, String statut) {
        this.id = id;
        this.type = type;
        this.trajectoire = trajectoire;
        this.statut = statut;
    }

    // Getters et setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTrajectoire() { return trajectoire; }
    public void setTrajectoire(String trajectoire) { this.trajectoire = trajectoire; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }


    //gerer les missions

    public void lancerMission() {
    this.statut = "En cours";
    System.out.println("Mission " + id + " lancée.");
}

public void terminerMission() {
    this.statut = "Terminée";
    System.out.println("Mission " + id + " terminée.");
}
}