package com.codecafe.grpc.protobuf.serialization_deserialization;

import com.codecafe.grpc.protobuf.model.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializationDemo {

    public static void main(String[] args) throws IOException {

        Person john = Person.newBuilder()
                .setName("John Wick")
                .setAge(42)
                .build();

        Path path = Paths.get("john.ser");
        Files.write(path, john.toByteArray());

    }

}