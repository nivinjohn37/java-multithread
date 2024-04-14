package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {
    HelloWorldService hws;

    public CompletableFutureHelloWorld(HelloWorldService helloWorldService) {
        this.hws = helloWorldService;
    }

    public CompletableFuture<String> helloWorld() {
        return CompletableFuture.supplyAsync(hws::helloWorld)
                .thenApply(String::toUpperCase);
    }

    public CompletableFuture<String> testLength() {
        return CompletableFuture.supplyAsync(() -> hws.testLength("test"))
                .thenApply((result) -> result + " - " + result.length());
    }

    public String helloworld_muliple_async_calls(){
        startTimer();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world());

        String hw= hello
                .thenCombine(world, (h, w) -> h+w) // first, second
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        return hw;
    }

    public String helloworld_muliple_async_calls2(){
        startTimer();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world());

        String hw= hello
                .thenCombine(world, (h, w) -> h+w) // first, second
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        return hw;
    }

    public String helloworld_3_async_calls(){
        startTimer();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world());
        CompletableFuture<String> hiCompletableFuture =  CompletableFuture.supplyAsync(()->{
            delay(1000);
            return " Hi CompletableFuture!";
        });

        String hw= hello
                .thenCombine(world, (h, w) -> h+w) // first, second
                .thenCombine(hiCompletableFuture, (previous,current)->previous+current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        return hw;
    }

    public String helloworld_4_async_calls(){
        startTimer();

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(()->hws.hello());
        CompletableFuture<String> world = CompletableFuture.supplyAsync(()->hws.world());
        CompletableFuture<String> hiCompletableFuture =  CompletableFuture.supplyAsync(()->{
            delay(1000);
            return " Hi CompletableFuture!";
        });

        CompletableFuture<String> hiCompletableFuture2 =  CompletableFuture.supplyAsync(()->{
            delay(1000);
            return " Bye!";
        });

        String hw= hello
                .thenCombine(world, (h, w) -> h+w) // first, second
                .thenCombine(hiCompletableFuture, (previous,current)->previous+current)
                .thenCombine(hiCompletableFuture2, (previous,current)->previous+current)
                .thenApply(String::toUpperCase)
                .join();

        timeTaken();
        return hw;
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
