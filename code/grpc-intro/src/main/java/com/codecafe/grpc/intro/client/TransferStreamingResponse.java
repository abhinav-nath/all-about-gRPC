package com.codecafe.grpc.intro.client;

import com.codecafe.grpc.intro.service.TransferResponse;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class TransferStreamingResponse implements StreamObserver<TransferResponse> {

    private CountDownLatch latch;

    public TransferStreamingResponse(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(TransferResponse transferResponse) {
        System.out.println("Transfer Status : " + transferResponse.getStatus());

        transferResponse.getAccountsList()
                .stream()
                .map(account -> account.getAccountNumber() + " : " + account.getAccountBalance())
                .forEach(System.out::println);

        System.out.println("-------------------------");
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
        this.latch.countDown();
    }

    @Override
    public void onCompleted() {
        System.out.println("All transfers done!");
        this.latch.countDown();
    }

}
