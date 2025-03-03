package com.testDassault.droneDassault.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testDassault.droneDassault.model.Drone;
import com.testDassault.droneDassault.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    // Créer un drone
    @PostMapping
    public ResponseEntity<Drone> createDrone(@RequestBody Drone drone) {
        Drone newDrone = droneService.createDrone(drone);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDrone);
    }

    @PostMapping("/import-data")
    public ResponseEntity<String> importData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/data.json");
            if (inputStream == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fichier data.json introuvable !");
            }
            List<Drone> drones = mapper.readValue(inputStream, new TypeReference<List<Drone>>() {
            });
            droneService.saveAllDrones(drones);
            return ResponseEntity.ok("Données importées avec succès !");
        } catch (Exception e) {
            e.printStackTrace(); // Log l'exception dans la console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'importation des données : " + e.getMessage());
        }
    }

    // Récupérer tous les drones
    @GetMapping
    public ResponseEntity<List<Drone>> getAllDrones() {
        return ResponseEntity.ok(droneService.getAllDrones());
    }

    // Récupérer un drone par son ID
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getDroneById(@PathVariable String id) {
        Optional<Drone> drone = droneService.getDroneById(id);
        if (drone.isPresent()) {
            return ResponseEntity.ok(drone.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Drone avec ID " + id + " introuvable !");
        }
    }

    // Mettre à jour un drone
    @PutMapping("/{id}")
    public ResponseEntity<Drone> updateDrone(@PathVariable String id, @RequestBody Drone droneDetails) {
        try {
            Drone updatedDrone = droneService.updateDrone(id, droneDetails);
            return ResponseEntity.ok(updatedDrone);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone not found", e);
        }
    }

    // Supprimer un drone
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrone(@PathVariable String id) {
        try {
            droneService.deleteDrone(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drone not found", e);
        }
    }

}
