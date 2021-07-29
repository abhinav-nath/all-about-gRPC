package com.codecafe.grpc.protobuf.serialization_deserialization;

import com.codecafe.grpc.protobuf.model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeserializationDemo {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("john.ser");

        byte[] bytes = Files.readAllBytes(path);
        Person newJohn = Person.parseFrom(bytes);

        System.out.println(newJohn);

    }

}