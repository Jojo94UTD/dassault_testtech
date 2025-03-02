
package com.testDassault.droneDassault.controller;

import com.testDassault.droneDassault.model.Drone;
import com.testDassault.droneDassault.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    // Créer un drone
    @PostMapping
    public Drone createDrone(@RequestBody Drone drone) {
        return droneService.createDrone(drone);
    }

    // Récupérer tous les drones
    @GetMapping
    public List<Drone> getAllDrones() {
        return droneService.getAllDrones();
    }

    // Récupérer un drone par son ID
    @GetMapping("/{id}")
    public Drone getDroneById(@PathVariable String id) {
        return droneService.getDroneById(id).orElseThrow(() -> new RuntimeException("Drone not found"));
    }

    // Mettre à jour un drone
    @PutMapping("/{id}")
    public Drone updateDrone(@PathVariable String id, @RequestBody Drone droneDetails) {
        return droneService.updateDrone(id, droneDetails);
    }

    // Supprimer un drone
    @DeleteMapping("/{id}")
    public void deleteDrone(@PathVariable String id) {
        droneService.deleteDrone(id);
    }

    
}