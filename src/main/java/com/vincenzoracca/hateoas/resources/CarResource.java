package com.vincenzoracca.hateoas.resources;

import com.vincenzoracca.hateoas.services.CarService;
import com.vincenzoracca.hateoas.dtos.CarDTO;
import com.vincenzoracca.hateoas.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarResource {

    private final CarService carService;

    public CarResource(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{plate}")
    public ResponseEntity findByPlate(@PathVariable String plate) {
        CarDTO car = carService.findByPlate(plate);
        if(car != null) return ResponseEntity.ok(car);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{plate}/user")
    public ResponseEntity findCarUser(@PathVariable String plate) {
        UserDTO user = carService.findCarUser(plate);
        if(user != null) return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }
}
