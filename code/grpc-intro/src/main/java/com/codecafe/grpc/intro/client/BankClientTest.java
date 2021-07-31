package com.codecafe.grpc.intro.client;

import com.codecafe.grpc.intro.model.Balance;
import com.codecafe.grpc.intro.model.BalanceCheckRequest;
import com.codecafe.grpc.intro.model.WithdrawRequest;
import com.codecafe.grpc.intro.service.BankServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BankClientTest {

    BankServiceGrpc.BankServiceBlockingStub blockingStub;

    @BeforeAll
    public void setup() {

        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8181)
                .usePlaintext()
                .build();

        this.blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);
    }

    @Test
    public void getBalanceTest() {

        BalanceCheckRequest balanceCheckRequest = BalanceCheckRequest.newBuilder()
                .setAccountNumber(7)
                .build();

        Balance balance = this.blockingStub.getBalance(balanceCheckRequest);

        System.out.println("Received : " + balance.getAmount());

    }

    @Test
    public void withdrawTest() {

        int accountNumber = 5;
        int amountToWithdraw = 505;

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

}