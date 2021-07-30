package com.codecafe.grpc.protobuf;

import com.codecafe.grpc.protobuf.model.Address;
import com.codecafe.grpc.protobuf.model.Car;
import com.codecafe.grpc.protobuf.model.Person;

public class CompositionDemo {

    public static void main(String[] args) {

        Address address = Address.newBuilder()
                .setZipCode(32812)
                .setStreet("123 Main St")
                .setCity("Chicago")
                .build();

        Car car = Car.newBuilder()
                .setMake("Mercedes")
                .setModel("Benz")
                .build();

        Person tony = Person.newBuilder()
                .setName("Tony Stark")
                .setAge(42)
                .setEmail("tony.stark@xyz.com")
                .setAddress(address)
                .setCar(car)
                .build();

        System.out.println(tony);

    }

}