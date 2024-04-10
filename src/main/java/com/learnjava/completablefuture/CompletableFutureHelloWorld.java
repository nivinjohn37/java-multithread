package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {
    HelloWorldService helloWorldService;

    public CompletableFutureHelloWorld(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(helloWorldService::helloWorld)
                .thenApply(String::toUpperCase);
    }

    public CompletableFuture<String> testLength() {
        return CompletableFuture.supplyAsync(() -> helloWorldService.testLength("test"))
                .thenApply((result) -> result + " - " + result.length());
    }

    public static void main(String[] args) {
        HelloWorldService helloWorldService = new HelloWorldService();


        log("Done");
        // Adding a sleep here to prevent the program from terminating immediately
        try {
            Thread.sleep(3000); // Wait for the CompletableFuture to complete
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
