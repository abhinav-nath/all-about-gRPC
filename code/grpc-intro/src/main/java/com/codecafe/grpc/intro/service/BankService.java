package com.codecafe.grpc.intro.service;

import com.codecafe.grpc.intro.model.*;
import com.codecafe.grpc.intro.persistence.AccountDatabase;
import com.codecafe.grpc.intro.server.DepositStreamingRequest;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase {

    // Unary RPC
    @Override
    public void getBalance(BalanceCheckRequest request, StreamObserver<Balance> responseObserver) {

        int accountNumber = request.getAccountNumber();

        Balance balance = Balance.newBuilder()
                .setAmount(AccountDatabase.getBalance(accountNumber))
                .build();

        responseObserver.onNext(balance);
        responseObserver.onCompleted();
    }

    // Server streaming RPC
    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<WithdrawResponse> withdrawResponseStreamObserver) {
        int accountNumber = request.getAccountNumber();
        int amountToWithdraw = request.getAmount();

        int balance = AccountDatabase.getBalance(accountNumber);

        // validations
        if (balance < amountToWithdraw) {
            Status status = Status.FAILED_PRECONDITION.withDescription("Not enough balance. You have only $" + balance);
            withdrawResponseStreamObserver.onError(status.asRuntimeException());
            return;
        }

        for (int i = 0; i < (amountToWithdraw / 10); i++) {
            WithdrawResponse response = WithdrawResponse.newBuilder()
                    .setAmount(10)
                    .build();
            withdrawResponseStreamObserver.onNext(response);

            AccountDatabase.deductBalance(accountNumber, 10);

            // simulate a delay of 1s for every $10
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        withdrawResponseStreamObserver.onCompleted();
    }

    // Client Streaming RPC
    @Override
    public StreamObserver<DepositRequest> deposit(StreamObserver<DepositResponse> depositResponseStreamObserver) {
        return new DepositStreamingRequest(depositResponseStreamObserver);
    }

}
