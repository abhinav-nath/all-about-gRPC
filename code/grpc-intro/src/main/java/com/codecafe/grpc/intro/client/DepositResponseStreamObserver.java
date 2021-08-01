package com.codecafe.grpc.intro.client;

import com.codecafe.grpc.intro.model.DepositResponse;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class DepositResponseStreamObserver implements StreamObserver<DepositResponse> {

    private CountDownLatch latch;

    public DepositResponseStreamObserver(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(DepositResponse depositResponse) {
        System.out.println("Final balance : $" + depositResponse.getBalance());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        System.out.println("Deposit completed!");
        latch.countDown();
    }

}
