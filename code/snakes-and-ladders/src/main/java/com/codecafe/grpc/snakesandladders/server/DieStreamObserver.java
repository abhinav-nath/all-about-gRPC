package com.codecafe.grpc.snakesandladders.server;

import com.codecafe.grpc.snakesandladders.model.SnakesAndLaddersMap;
import com.codecafe.grpc.snakesandladders.service.Die;
import com.codecafe.grpc.snakesandladders.service.GameState;
import com.codecafe.grpc.snakesandladders.service.Player;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.ThreadLocalRandom;

public class DieStreamObserver implements StreamObserver<Die> {

    private Player client;
    private Player server;
    private StreamObserver<GameState> gameStateStreamObserver;

    public DieStreamObserver(Player client, Player server, StreamObserver<GameState> gameStateStreamObserver) {
        this.client = client;
        this.server = server;
        this.gameStateStreamObserver = gameStateStreamObserver;
    }

    @Override
    public void onNext(Die die) {
        // client plays first
        this.client = this.getNewPlayerPosition(this.client, die.getValue());

        if (this.client.getPosition() != 100) {
            // server plays only when client has played first and has not won yet
            this.server = this.getNewPlayerPosition(this.server, ThreadLocalRandom.current().nextInt(1, 7));
        }

        // client can play next chance only when it receives the GameState from server
        this.gameStateStreamObserver.onNext(this.getGameState());
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        this.gameStateStreamObserver.onCompleted();
    }

    private Player getNewPlayerPosition(Player player, int dieValue) {
        int position = player.getPosition() + dieValue;

        // get new position if a ladder or snake is encountered
        position = SnakesAndLaddersMap.getNewPosition(player.getName(), position);

        if (position <= 100) {
            player = player.toBuilder()
                    .setPosition(position)
                    .build();
        }

        return player;
    }

    private GameState getGameState() {
        return GameState.newBuilder()
                .addPlayer(this.client)
                .addPlayer(this.server)
                .build();
    }

}
