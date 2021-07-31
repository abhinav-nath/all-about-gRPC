package com.codecafe.grpc.intro.service;

import com.codecafe.grpc.intro.model.Balance;
import com.codecafe.grpc.intro.model.BalanceCheckRequest;
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

}
