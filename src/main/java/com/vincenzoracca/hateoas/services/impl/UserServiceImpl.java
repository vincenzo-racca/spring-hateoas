package com.vincenzoracca.hateoas.services.impl;

import com.vincenzoracca.hateoas.entities.User;
import com.vincenzoracca.hateoas.repos.UserRepository;
import com.vincenzoracca.hateoas.assemblers.CarAssembler;
import com.vincenzoracca.hateoas.assemblers.UserAssembler;
import com.vincenzoracca.hateoas.dtos.CarDTO;
import com.vincenzoracca.hateoas.dtos.UserDTO;
import com.vincenzoracca.hateoas.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserAssembler userAssembler;

    private final CarAssembler carAssembler;

    private final PagedResourcesAssembler pagedResourcesAssembler;


    public UserServiceImpl(UserRepository userRepository, UserAssembler userAssembler, PagedResourcesAssembler pagedResourcesAssembler, CarAssembler carAssembler) {
        this.userRepository = userRepository;
        this.userAssembler = userAssembler;
        this.carAssembler = carAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }


    @Override
    public CollectionModel<UserDTO> findAll(int page, int size, String[] sort, String dir) {
        PageRequest pageRequest;
        Sort.Direction direction;
        if(sort == null) {
            pageRequest = PageRequest.of(page, size);
        }
        else {
            if(dir.equalsIgnoreCase("asc")) direction = Sort.Direction.ASC;
            else direction = Sort.Direction.DESC;
            pageRequest = PageRequest.of(page, size, Sort.by(direction, sort));
        }
        Page<User> users = userRepository.findAll(pageRequest);
        if(! CollectionUtils.isEmpty(users.getContent())) return pagedResourcesAssembler.toModel(users, userAssembler);
        return null;
    }

    @Override
    public UserDTO findByCode(String code) {
        User user = userRepository.findByCode(code).orElse(null);
        if(user != null) {
            return userAssembler.toModel(user);
        }
        return null;
    }

    @Override
    public CollectionModel<CarDTO> findUserCars(String code) {
        User user = userRepository.findByCode(code).orElse(null);
        if(user != null && (! CollectionUtils.isEmpty(user.getCars())) ) {
            return carAssembler.toCollectionModel(user.getCars());
        }
        return null;
    }

    @Transactional
    @Override
    public UserDTO insert(User user) {
        user.getCars().forEach(car -> car.setUser(user));
        return userAssembler.toModel(userRepository.save(user));
    }
}
