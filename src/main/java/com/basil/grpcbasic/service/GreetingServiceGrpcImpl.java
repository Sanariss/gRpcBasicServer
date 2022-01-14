package com.basil.grpcbasic.service;

import com.basil.grpc.GreetingServiceGrpc;
import com.basil.grpc.GreetingServiceOuterClass;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GreetingServiceGrpcImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    public static final Logger LOGGER = LoggerFactory.getLogger(GreetingServiceGrpcImpl.class);

    /**
     *
     */
    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responseObserver) {
        LOGGER.info("Request - {}", request);

        GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.HelloResponse
                .newBuilder()
                .setGreeting("Hello from server, " + request.getName())
                .build();

        //отсылаем ответ
        responseObserver.onNext(response);

        //больше не будем пересылать данные
        responseObserver.onCompleted();
    }

}
