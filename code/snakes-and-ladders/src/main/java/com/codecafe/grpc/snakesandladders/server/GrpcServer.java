package com.codecafe.grpc.snakesandladders.server;

import com.codecafe.grpc.snakesandladders.service.SnakesAndLaddersService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(8181)
                .addService(new SnakesAndLaddersService())
                .build();

        server.start();

        server.awaitTermination();
    }

}