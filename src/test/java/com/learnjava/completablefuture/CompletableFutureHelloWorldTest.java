package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {
    HelloWorldService helloWorldService = new HelloWorldService();
    CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(helloWorldService);
    @Test
    void helloWorld() {
        //given

        //when
        CompletableFuture<String> completableFuture = cfhw.helloWorld();
        //then
        completableFuture.thenAccept((s) ->{
            assertEquals("HELLO WORLD", s);
        }).join();
    }

    @Test
    void testLength() {
        //given

        //when
        CompletableFuture<String> completableFuture = cfhw.testLength();
        //then
        completableFuture.thenAccept((s) ->{
            assertEquals("test - 4", s);
        }).join();
    }
}