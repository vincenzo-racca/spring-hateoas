package com.vincenzoracca.hateoas.services.impl;

import com.vincenzoracca.hateoas.assemblers.UserAssembler;
import com.vincenzoracca.hateoas.entities.Car;
import com.vincenzoracca.hateoas.repos.CarRepository;
import com.vincenzoracca.hateoas.services.CarService;
import com.vincenzoracca.hateoas.assemblers.CarAssembler;
import com.vincenzoracca.hateoas.dtos.CarDTO;
import com.vincenzoracca.hateoas.dtos.UserDTO;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    private final UserAssembler userAssembler;

    private final CarAssembler carAssembler;

    private final PagedResourcesAssembler pagedResourcesAssembler;

    public CarServiceImpl(CarRepository carRepository, UserAssembler userAssembler, CarAssembler carAssembler, PagedResourcesAssembler pagedResourcesAssembler) {
        this.carRepository = carRepository;
        this.userAssembler = userAssembler;
        this.carAssembler = carAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }


    @Override
    public CarDTO findByPlate(String plate) {
        Car car = carRepository.findByPlate(plate).orElse(null);
        if(car != null) {
            return carAssembler.toModel(car);
        }
        return null;
    }

    @Override
    public UserDTO findCarUser(String plate) {
        Car car = carRepository.findByPlate(plate).orElse(null);
        if(car != null && car.getUser() != null) {
            return userAssembler.toModel(car.getUser());
        }
        return null;
    }
}
