package com.vincenzoracca.hateoas.assemblers;

import com.vincenzoracca.hateoas.entities.User;
import com.vincenzoracca.hateoas.dtos.UserDTO;
import com.vincenzoracca.hateoas.resources.UserResource;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserAssembler implements RepresentationModelAssembler<User, UserDTO> {


    @Override
    public UserDTO toModel(User entity) {
        UserDTO userDTO = new UserDTO(entity.getName(), entity.getSurname(), entity.getCode(), entity.getAddress());
        userDTO.add(linkTo(methodOn(UserResource.class).findUserCars(entity.getCode())).withRel("cars"));
        userDTO.add(linkTo(methodOn(UserResource.class).findByCode(entity.getCode())).withSelfRel());
        return userDTO;
    }
}
