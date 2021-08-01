package com.codecafe.grpc.snakesandladders.client;

import com.codecafe.grpc.snakesandladders.service.Die;
import com.codecafe.grpc.snakesandladders.service.GameState;
import com.codecafe.grpc.snakesandladders.service.Player;
import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class GameStateStreamObserver implements StreamObserver<GameState> {

    private StreamObserver<Die> dieStreamObserver;
    private CountDownLatch latch;

    public GameStateStreamObserver(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(GameState gameState) {
        List<Player> players = gameState.getPlayerList();

        players.forEach(p -> System.out.println(p.getName() + " : " + p.getPosition()));

        boolean isGameOver = players.stream()
                .anyMatch(p -> p.getPosition() == 100);

        if (isGameOver) {
            System.out.println("Game Over!");
            this.dieStreamObserver.onCompleted();
        } else {
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            this.rollDie();
        }

        System.out.println("---------------------------");

    }

    @Override
    public void onError(Throwable throwable) {
        this.latch.countDown();
    }

    @Override
    public void onCompleted() {
        this.latch.countDown();
    }

    public void setDieStreamObserver(StreamObserver<Die> dieStreamObserver) {
        this.dieStreamObserver = dieStreamObserver;
    }

    // starting point of the game
    public void rollDie() {
        int dieValue = ThreadLocalRandom.current().nextInt(1, 7);

        Die die = Die.newBuilder()
                .setValue(dieValue)
                .build();

        this.dieStreamObserver.onNext(die);
    }

}