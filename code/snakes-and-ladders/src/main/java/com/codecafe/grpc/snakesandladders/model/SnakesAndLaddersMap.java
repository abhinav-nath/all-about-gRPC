package com.codecafe.grpc.snakesandladders.model;

import java.util.HashMap;
import java.util.Map;

public class SnakesAndLaddersMap {

    private static final Map<Integer, Integer> LADDERS_MAP = new HashMap<>();
    private static final Map<Integer, Integer> SNAKES_MAP = new HashMap<>();

    static {
        // ladders
        LADDERS_MAP.put(1, 38);
        LADDERS_MAP.put(4, 14);
        LADDERS_MAP.put(8, 30);
        LADDERS_MAP.put(21, 42);
        LADDERS_MAP.put(28, 76);
        LADDERS_MAP.put(50, 67);
        LADDERS_MAP.put(71, 92);
        LADDERS_MAP.put(80, 99);

        // snakes
        SNAKES_MAP.put(32, 10);
        SNAKES_MAP.put(36, 6);
        SNAKES_MAP.put(48, 26);
        SNAKES_MAP.put(62, 18);
        SNAKES_MAP.put(88, 24);
        SNAKES_MAP.put(95, 56);
        SNAKES_MAP.put(97, 78);
    }

    public static int getNewPosition(String playerName, int position) {
        if (LADDERS_MAP.get(position) != null) {
            System.out.println(playerName + " climbed up the ladder from " + position + " to " + LADDERS_MAP.get(position));
            return LADDERS_MAP.get(position);
        } else if (SNAKES_MAP.get(position) != null) {
            System.out.println(playerName + " bit by the snake from " + position + " to " + SNAKES_MAP.get(position));
            return SNAKES_MAP.get(position);
        }

        System.out.println(playerName + " moved to " + position);
        return position;
    }

}