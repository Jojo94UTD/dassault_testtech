package com.testDassault.droneDassault.service;

import com.testDassault.droneDassault.model.Drone;
import com.testDassault.droneDassault.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneService {

    @Autowired
    private DroneRepository droneRepository;

    // Créer un drone
    public Drone createDrone(Drone drone) {
        return droneRepository.save(drone);
    }

    // Récupérer tous les drones
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    // Récupérer un drone par son ID
    public Optional<Drone> getDroneById(String id) {
        return droneRepository.findById(id);
    }

    // Mettre à jour un drone
    public Drone updateDrone(String id, Drone droneDetails) {
        Drone drone = droneRepository.findById(id).orElseThrow(() -> new RuntimeException("Drone not found"));
        drone.setName(droneDetails.getName());
        drone.setLatitude(droneDetails.getLatitude());
        drone.setLongitude(droneDetails.getLongitude());
        drone.setBatteryLevel(droneDetails.getBatteryLevel());
        drone.setStatus(droneDetails.getStatus());
        return droneRepository.save(drone);
    }

    public void saveAllDrones(List<Drone> drones) {
        droneRepository.saveAll(drones);
    }

    // Supprimer un drone
    public void deleteDrone(String id) {
        droneRepository.deleteById(id);
    }
}