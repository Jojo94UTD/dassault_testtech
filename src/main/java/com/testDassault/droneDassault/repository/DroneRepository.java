package com.testDassault.droneDassault.repository;

import com.testDassault.droneDassault.model.Drone;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DroneRepository extends MongoRepository<Drone, String> {
    // Tu peux ajouter des méthodes personnalisées ici si nécessaire
}