package com.testDassault.droneDassault.controller;

import com.testDassault.droneDassault.model.Drone;
import com.testDassault.droneDassault.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // Permet d'envoyer des messages WebSocket dynamiquement

    private final Random random = new Random(); // Pour modifier les valeurs aléatoirement


    // 🛠️ Créer un drone
    @PostMapping
    public ResponseEntity<Drone> createDrone(@RequestBody Drone drone) {
        Drone newDrone = droneService.createDrone(drone);
        
        // 📡 Envoyer le nouveau drone via WebSocket
        messagingTemplate.convertAndSend("/topic/drones", newDrone);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newDrone);
    }

    // 🛠️ Récupérer tous les drones
    @GetMapping("/all")
    public ResponseEntity<List<Drone>> getAllDrones() {
        return ResponseEntity.ok(droneService.getAllDrones());
    }

    // 🛠️ Récupérer un drone par ID
    @GetMapping("/{id}")
    public ResponseEntity<Drone> getDroneById(@PathVariable String id) {
        return droneService.getDroneById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // 🛠️ Mettre à jour un drone
    @PutMapping("/{id}")
    public ResponseEntity<Drone> updateDrone(@PathVariable String id, @RequestBody Drone droneDetails) {
        try {
            Drone updatedDrone = droneService.updateDrone(id, droneDetails);
            
            // 📡 Envoyer la mise à jour via WebSocket
            messagingTemplate.convertAndSend("/topic/drones", updatedDrone);
            
            return ResponseEntity.ok(updatedDrone);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 🛠️ Supprimer un drone
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrone(@PathVariable String id) {
        try {
            droneService.deleteDrone(id);
            
            // 📡 Informer via WebSocket qu'un drone a été supprimé
            messagingTemplate.convertAndSend("/topic/drones/delete", id);
            
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 📡 WebSocket - Envoyer **chaque drone un par un**
    @MessageMapping("/getDrones")
    public void sendDrones() {
        List<Drone> drones = droneService.getAllDrones();
        if (drones.isEmpty()) {
            System.out.println("⚠️ Aucun drone trouvé !");
        } else {
            for (Drone drone : drones) {
                System.out.println("📡 Envoi du drone via WebSocket : " + drone);
                messagingTemplate.convertAndSend("/topic/drones", drone);
            }
        }
    }


    @Scheduled(fixedRate = 5000)
public void updateDronePositions() {
    List<Drone> drones = droneService.getAllDrones();

    if (!drones.isEmpty()) {
        for (Drone drone : drones) {
            // 🎯 Simuler un changement de position et de batterie
            drone.setLatitude(drone.getLatitude() + (random.nextDouble() - 0.5) * 0.001);
            drone.setLongitude(drone.getLongitude() + (random.nextDouble() - 0.5) * 0.001);
            drone.setBatteryLevel(Math.max(0, drone.getBatteryLevel() - random.nextInt(3))); // Batterie diminue

            droneService.updateDrone(drone.getId(), drone); // Sauvegarde en base

            messagingTemplate.convertAndSend("/topic/drones", drone); // 📡 Envoi au frontend
        }
        System.out.println("📡 Mise à jour envoyée !");
    }
}

}
