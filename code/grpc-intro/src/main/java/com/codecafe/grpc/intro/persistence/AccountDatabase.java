package com.codecafe.grpc.intro.persistence;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Simple Map based simulation of a database

public class AccountDatabase {

    private static final Map<Integer, Integer> ACCOUNT_MAP = IntStream.rangeClosed(1, 10)
            .boxed()
            .collect(Collectors.toMap(
                    Function.identity(),
                    v -> 100)
            );

    public static Integer getBalance(int accountNumber) {
        return ACCOUNT_MAP.get(accountNumber);
    }

    public static Integer addBalance(int accountNumber, int amount) {
        return ACCOUNT_MAP.computeIfPresent(accountNumber, (k, v) -> v + amount);
    }

    public static Integer deductBalance(int accountNumber, int amount) {
        return ACCOUNT_MAP.computeIfPresent(accountNumber, (k, v) -> v - amount);
    }

    public static void printAccountDetails() {
        System.out.println(ACCOUNT_MAP);
    }

}
