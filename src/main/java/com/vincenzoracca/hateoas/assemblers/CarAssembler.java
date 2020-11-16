package com.vincenzoracca.hateoas.assemblers;

import com.vincenzoracca.hateoas.entities.Car;
import com.vincenzoracca.hateoas.resources.CarResource;
import com.vincenzoracca.hateoas.dtos.CarDTO;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarAssembler implements RepresentationModelAssembler<Car, CarDTO> {

    @Override
    public CarDTO toModel(Car entity) {
        CarDTO carDTO = new CarDTO(entity.getPlate(), entity.getName());
        carDTO.add(WebMvcLinkBuilder.linkTo(methodOn(CarResource.class).findCarUser(entity.getPlate())).withRel("user"));
        carDTO.add(linkTo(methodOn(CarResource.class).findByPlate(entity.getPlate())).withSelfRel());
        return carDTO;
    }
}
