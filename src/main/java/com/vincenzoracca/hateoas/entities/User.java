package com.vincenzoracca.hateoas.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String code;

    private String address;

    @OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private List<Car> cars = new ArrayList<>();


    public void addCar(Car car) {
        car.setUser(this);
        this.cars.add(car);
    }

    public void removeCar(Car car) {
        this.cars.remove(car);
        car.setUser(null);
    }



}
