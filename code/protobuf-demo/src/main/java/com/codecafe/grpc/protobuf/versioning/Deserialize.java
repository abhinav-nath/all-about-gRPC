package com.codecafe.grpc.protobuf.versioning;

import com.codecafe.grpc.protobuf.model.Television;
//import com.codecafe.grpc.protobuf.model.TvType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Deserialize {

    public static void main(String[] args) throws IOException {

        Path pathV1 = Paths.get("src/main/java/com/codecafe/grpc/protobuf/versioning/tv-v1");
        Path pathV2 = Paths.get("src/main/java/com/codecafe/grpc/protobuf/versioning/tv-v2");
        Path pathV3 = Paths.get("src/main/java/com/codecafe/grpc/protobuf/versioning/tv-v3");
        Path pathV4 = Paths.get("src/main/java/com/codecafe/grpc/protobuf/versioning/tv-v4");

        // de-serialize

        // byte[] bytesV1 = Files.readAllBytes(pathV1);
        // byte[] bytesV2 = Files.readAllBytes(pathV2);
        // byte[] bytesV3 = Files.readAllBytes(pathV3);
        byte[] bytesV4 = Files.readAllBytes(pathV4);
        // byte[] bytesV1 = Files.readAllBytes(pathV2);

        // System.out.println("bytes V1:\n" + Television.parseFrom(bytesV1));
        // System.out.println("bytes V2:\n" + Television.parseFrom(bytesV2));
        // System.out.println("bytes V4:\n" + Television.parseFrom(bytesV4));
        System.out.println("bytes V4:\n" + Television.parseFrom(bytesV4));
        // System.out.println("bytes V1 from V2 model:\n" + Television.parseFrom(bytesV1));

        // Television television = Television.parseFrom(bytesV1);
        // Television television = Television.parseFrom(bytesV2);
        // Television television = Television.parseFrom(bytesV3);
        Television television = Television.parseFrom(bytesV4);

        // System.out.println(television.getType()); // default value

    }

}