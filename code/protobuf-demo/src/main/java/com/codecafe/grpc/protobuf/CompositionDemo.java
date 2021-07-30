package com.codecafe.grpc.protobuf;

import com.codecafe.grpc.protobuf.model.Address;
import com.codecafe.grpc.protobuf.model.Car;
import com.codecafe.grpc.protobuf.model.CarType;
import com.codecafe.grpc.protobuf.model.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompositionDemo {

    public static void main(String[] args) {

        Address address = Address.newBuilder()
                .setZipCode(32812)
                .setStreet("123 Main St")
                .setCity("Chicago")
                .build();

        Car benz = Car.newBuilder()
                .setMake("Lamborghini")
                .setModel("Veneno")
                .setCarType(CarType.SPORTS)
                .build();

        Car db11 = Car.newBuilder()
                .setMake("Aston Martin")
                .setModel("DB11")
                .setCarType(CarType.SEDAN)
                .build();

        List<Car> cars = new ArrayList<>();
        cars.add(benz);
        cars.add(db11);

        Map<String, String> identityMap = new HashMap<>();
        identityMap.put("ssn", "100001");
        identityMap.put("taxid", "20002");

        Person tony = Person.newBuilder()
                .setName("Tony Stark")
                .setAge(42)
                .setEmail("tony.stark@xyz.com")
                .putAllPersonalIdentification(identityMap)
                .setAddress(address)
                .addAllCar(cars)
                .build();

        System.out.println(tony);
        System.out.println(tony.getPersonalIdentificationOrDefault("ssn", "30003"));

    }

}