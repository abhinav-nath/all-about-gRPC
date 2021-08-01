package com.codecafe.grpc.snakesandladders.service;

import com.codecafe.grpc.snakesandladders.server.DieStreamObserver;
import io.grpc.stub.StreamObserver;

public class SnakesAndLaddersService extends SnakesAndLaddersServiceGrpc.SnakesAndLaddersServiceImplBase {

    @Override
    public StreamObserver<Die> rollDie(StreamObserver<GameState> responseObserver) {

        Player client = Player.newBuilder()
                .setName("Client")
                .setPosition(0)
                .build();

        Player server = Player.newBuilder()
                .setName("Server")
                .setPosition(0)
                .build();

        return new DieStreamObserver(client, server, responseObserver);
    }

}