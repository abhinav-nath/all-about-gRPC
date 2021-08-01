package com.codecafe.grpc.snakesandladders.client;

import com.codecafe.grpc.snakesandladders.service.Die;
import com.codecafe.grpc.snakesandladders.service.SnakesAndLaddersServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.CountDownLatch;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SnakesAndLaddersGameClientTest {

    private SnakesAndLaddersServiceGrpc.SnakesAndLaddersServiceStub stub;

    @BeforeAll
    public void setup() {

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8181)
                .usePlaintext()
                .build();

        this.stub = SnakesAndLaddersServiceGrpc.newStub(managedChannel);
    }

    @Test
    public void startGame() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        GameStateStreamObserver gameStateStreamObserver = new GameStateStreamObserver(latch);
        StreamObserver<Die> dieStreamObserver = this.stub.rollDie(gameStateStreamObserver);
        gameStateStreamObserver.setDieStreamObserver(dieStreamObserver);
        gameStateStreamObserver.rollDie();
        latch.await();
    }

}