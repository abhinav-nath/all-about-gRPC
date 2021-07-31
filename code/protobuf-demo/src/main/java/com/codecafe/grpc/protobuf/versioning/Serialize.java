package com.codecafe.grpc.protobuf.versioning;

import com.codecafe.grpc.protobuf.model.Television;
import com.codecafe.grpc.protobuf.model.TvType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Serialize {

    public static void main(String[] args) throws IOException {

        Path pathV1 = Paths.get("src/main/java/com/codecafe/grpc/protobuf/versioning/tv-v1");
        Path pathV2 = Paths.get("src/main/java/com/codecafe/grpc/protobuf/versioning/tv-v2");
        Path pathV3 = Paths.get("src/main/java/com/codecafe/grpc/protobuf/versioning/tv-v3");
        Path pathV4 = Paths.get("src/main/java/com/codecafe/grpc/protobuf/versioning/tv-v4");

        // v1
        /*
        Television televisionV1 = Television.newBuilder()
                .setBrand("LG")
                .setYear(2020)
                .build();
                */

        // v2
        /*
        Television televisionV2 = Television.newBuilder()
                .setBrand("LG")
                .setModel(2020)
                .setType(TvType.UHD)
                .build();
                */

        // v3
        /*
        Television televisionV3 = Television.newBuilder()
                .setBrand("LG")
                .setType(TvType.OLED)
                .build();
                */

        // v4
        Television televisionV4 = Television.newBuilder()
                .setBrand("LG")
                .setType(TvType.OLED)
                .setPrice(30000)
                .build();

        // serialize
        // Files.write(pathV1, televisionV1.toByteArray());
        // Files.write(pathV2, televisionV2.toByteArray());
        // Files.write(pathV3, televisionV3.toByteArray());
        Files.write(pathV4, televisionV4.toByteArray());
    }

}