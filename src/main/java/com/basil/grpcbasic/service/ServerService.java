package com.basil.grpcbasic.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ServerService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ServerService.class);

    @EventListener(ApplicationStartedEvent.class)
    public void startServerAfterSpring() throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new GreetingServiceGrpcImpl())
                .build();

        server.start();

        LOGGER.info("gRpc Server Started");

        server.awaitTermination();
    }

}
