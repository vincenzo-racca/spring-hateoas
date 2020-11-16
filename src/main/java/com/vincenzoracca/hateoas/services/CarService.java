package com.vincenzoracca.hateoas.services;

import com.vincenzoracca.hateoas.dtos.CarDTO;
import com.vincenzoracca.hateoas.dtos.UserDTO;

public interface CarService {

    CarDTO findByPlate(String plate);

    UserDTO findCarUser(String plate);
}
