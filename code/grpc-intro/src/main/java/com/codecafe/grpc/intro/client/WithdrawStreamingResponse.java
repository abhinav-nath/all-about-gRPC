package com.codecafe.grpc.intro.client;

import com.codecafe.grpc.intro.model.WithdrawResponse;
import io.grpc.stub.StreamObserver;

public class WithdrawStreamingResponse implements StreamObserver<WithdrawResponse> {

    @Override
    public void onNext(WithdrawResponse withdrawResponse) {
        System.out.println("Received async : " + withdrawResponse.getAmount());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        System.out.println("Request completed successfully!");
    }

}
