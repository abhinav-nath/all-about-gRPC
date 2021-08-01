package com.codecafe.grpc.intro.service;

import com.codecafe.grpc.intro.server.TransferStreamingRequest;
import io.grpc.stub.StreamObserver;

public class TransferService extends TransferServiceGrpc.TransferServiceImplBase {

    @Override
    public StreamObserver<TransferRequest> transfer(StreamObserver<TransferResponse> transferResponseStreamObserver) {
        return new TransferStreamingRequest(transferResponseStreamObserver);
    }

}