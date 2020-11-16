package com.vincenzoracca.hateoas.services;

import com.vincenzoracca.hateoas.entities.User;
import com.vincenzoracca.hateoas.dtos.CarDTO;
import com.vincenzoracca.hateoas.dtos.UserDTO;
import org.springframework.hateoas.CollectionModel;

public interface UserService {

    CollectionModel<UserDTO> findAll(int page, int size, String[] sort, String dir);

    UserDTO findByCode(String code);

    CollectionModel<CarDTO> findUserCars(String code);

    UserDTO insert(User user);
}
