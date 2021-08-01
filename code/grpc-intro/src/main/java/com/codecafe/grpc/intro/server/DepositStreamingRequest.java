package com.codecafe.grpc.intro.server;

import com.codecafe.grpc.intro.model.DepositRequest;
import com.codecafe.grpc.intro.model.DepositResponse;
import com.codecafe.grpc.intro.persistence.AccountDatabase;
import io.grpc.stub.StreamObserver;

public class DepositStreamingRequest implements StreamObserver<DepositRequest> {

    private StreamObserver<DepositResponse> depositResponseStreamObserver;
    private int accountBalance;

    public DepositStreamingRequest(StreamObserver<DepositResponse> depositResponseStreamObserver) {
        this.depositResponseStreamObserver = depositResponseStreamObserver;
    }

    @Override
    public void onNext(DepositRequest depositRequest) {
        int accountNumber = depositRequest.getAccountNumber();
        int amount = depositRequest.getAmount();
        this.accountBalance = AccountDatabase.addBalance(accountNumber, amount);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        DepositResponse depositResponse = DepositResponse.newBuilder()
                .setBalance(this.accountBalance)
                .build();

        this.depositResponseStreamObserver.onNext(depositResponse);
        this.depositResponseStreamObserver.onCompleted();
    }

}
