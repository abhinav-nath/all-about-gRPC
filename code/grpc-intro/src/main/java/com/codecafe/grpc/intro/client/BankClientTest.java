package com.codecafe.grpc.intro.client;

import com.codecafe.grpc.intro.model.Balance;
import com.codecafe.grpc.intro.model.BalanceCheckRequest;
import com.codecafe.grpc.intro.model.WithdrawRequest;
import com.codecafe.grpc.intro.service.BankServiceGrpc;
import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankClientTest {

    BankServiceGrpc.BankServiceBlockingStub blockingStub;
    BankServiceGrpc.BankServiceStub bankServiceStub;

    @BeforeAll
    public void setup() {

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8181)
                .usePlaintext()
                .build();

        // Sync
        this.blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);

        // Async
        this.bankServiceStub = BankServiceGrpc.newStub(managedChannel);
    }

    @Test
    public void getBalanceTest() {

        int accountNumber = 5;

        BalanceCheckRequest balanceCheckRequest = BalanceCheckRequest.newBuilder()
                .setAccountNumber(accountNumber)
                .build();

        Balance balance = this.blockingStub.getBalance(balanceCheckRequest);

        System.out.println("Received : " + balance.getAmount());

    }

    @Test
    public void withdrawTest() {

        int accountNumber = 5;
        int amountToWithdraw = 40;

        BalanceCheckRequest balanceCheckRequest = BalanceCheckRequest.newBuilder()
                .setAccountNumber(accountNumber)
                .build();

        Balance balance = this.blockingStub.getBalance(balanceCheckRequest);

        System.out.println("Current balance : $" + balance.getAmount());

        System.out.println("Withdrawing $" + amountToWithdraw);

        WithdrawRequest withdrawRequest = WithdrawRequest.newBuilder()
                .setAccountNumber(accountNumber)
                .setAmount(amountToWithdraw)
                .build();

        this.blockingStub.withdraw(withdrawRequest)
                .forEachRemaining(response -> System.out.println("Received : $" + response.getAmount()));

        balanceCheckRequest = BalanceCheckRequest.newBuilder()
                .setAccountNumber(accountNumber)
                .build();

        balance = this.blockingStub.getBalance(balanceCheckRequest);

        System.out.println("Final balance : $" + balance.getAmount());

    }

    @Test
    public void withdrawAsyncTest() {

        int accountNumber = 5;
        int amountToWithdraw = 40;

        WithdrawRequest withdrawRequest = WithdrawRequest.newBuilder()
                .setAccountNumber(accountNumber)
                .setAmount(amountToWithdraw)
                .build();

        this.bankServiceStub.withdraw(withdrawRequest, new WithdrawStreamingResponse());

        Uninterruptibles.sleepUninterruptibly(6, TimeUnit.SECONDS);
    }

}