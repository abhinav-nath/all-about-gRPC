package com.codecafe.grpc.intro.server;

import com.codecafe.grpc.intro.service.BankService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcService {

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(8181)
                .addService(new BankService())
                .build();

        server.start();

        server.awaitTermination();
    }

}