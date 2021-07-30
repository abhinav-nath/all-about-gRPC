package com.codecafe.grpc.protobuf.json_vs_proto;

import com.codecafe.grpc.protobuf.model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;

// Comparison of JSON and Protobuf Serialization/Deserialization performance

public class JsonVsProtoPerformanceTest {

    public static void main(String[] args) {

        JPerson personJ = new JPerson();
        personJ.setName("tom");
        personJ.setAge(28);
        personJ.setEmail("abc@xyz.com");

        ObjectMapper mapper = new ObjectMapper();

        // JSON serialization and deserialization
        Runnable jsonSerializeDeserialize = () -> {
            try {
                // serializing
                byte[] bytes = mapper.writeValueAsBytes(personJ);

                // deserializing
                JPerson personJ1 = mapper.readValue(bytes, JPerson.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // Protobuf serialization and deserialization
        Person personP = Person.newBuilder()
                .setName("tom")
                .setAge(28)
                .setEmail("abc@xyz.com")
                .build();

        Runnable protoSerializeDeserialize = () -> {

            // serializing
            byte[] bytes = personP.toByteArray();
            try {
                // deserializing
                Person personP1 = Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        };

        for (int i = 1; i <= 5; i++) {
            System.out.println("\nPass #" + i);
            runPerformanceTest(jsonSerializeDeserialize, "jsonSerializeDeserialize");
            runPerformanceTest(protoSerializeDeserialize, "protoSerializeDeserialize");
        }

    }

    private static void runPerformanceTest(Runnable runnable, String method) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 5_000_000; i++) {
            runnable.run();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("method " + method + " took " + (endTime - startTime) + " ms");
    }

}