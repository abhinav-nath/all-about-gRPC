package com.codecafe.grpc.intro.client;

import com.codecafe.grpc.intro.model.WithdrawResponse;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class WithdrawStreamingResponse implements StreamObserver<WithdrawResponse> {

    private CountDownLatch latch;

    public WithdrawStreamingResponse(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onNext(WithdrawResponse withdrawResponse) {
        System.out.println("Received async : " + withdrawResponse.getAmount());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        System.out.println("Request completed successfully!");
        latch.countDown();
    }

}
