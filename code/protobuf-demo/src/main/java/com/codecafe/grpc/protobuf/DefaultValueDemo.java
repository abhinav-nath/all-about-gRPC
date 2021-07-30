package com.codecafe.grpc.protobuf;

import com.codecafe.grpc.protobuf.model.Person;

public class DefaultValueDemo {

    public static void main(String[] args) {

        Person person = Person.newBuilder().build();

        // should have thrown NPE
        System.out.println("City : " + person.getAddress().getCity());

        // There is no null in Protobuf
        // If there is a value then it is set
        // otherwise a default value is returned
        // This is both positive and negative

        // to avoid default values, protobuf has given this
        System.out.println(person.hasAddress());

    }

}
