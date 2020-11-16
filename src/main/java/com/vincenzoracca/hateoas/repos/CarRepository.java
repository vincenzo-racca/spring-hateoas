package com.vincenzoracca.hateoas.repos;

import com.vincenzoracca.hateoas.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByPlate(String plate);
}
