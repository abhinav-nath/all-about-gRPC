package com.codecafe.grpc.intro.service;

import com.codecafe.grpc.intro.model.Balance;
import com.codecafe.grpc.intro.model.BalanceCheckRequest;
import com.codecafe.grpc.intro.model.WithdrawRequest;
import com.codecafe.grpc.intro.model.WithdrawResponse;
import com.codecafe.grpc.intro.persistence.AccountDatabase;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {

        int accountNumber = request.getAccountNumber();

        Balance balance = Balance.newBuilder()
                .setAmount(AccountDatabase.getBalance(accountNumber))
                .build();

        responseObserver.onNext(balance);
        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<WithdrawResponse> responseObserver) {
        int accountNumber = request.getAccountNumber();
        int amountToWithdraw = request.getAmount();

        int balance = AccountDatabase.getBalance(accountNumber);

        for (int i = 0; i < (amountToWithdraw / 10); i++) {
            WithdrawResponse response = WithdrawResponse.newBuilder()
                    .setAmount(10)
                    .build();
            responseObserver.onNext(response);

            AccountDatabase.deductBalance(accountNumber, 10);

            // simulate a delay of 1s for every $10
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        responseObserver.onCompleted();
    }

}
