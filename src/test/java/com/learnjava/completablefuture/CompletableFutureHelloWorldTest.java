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

    @Test
    void helloworld_muliple_async_calls() {
        //given

        //when
        String helloWorld = cfhw.helloworld_muliple_async_calls();

        //then
        assertEquals("HELLO WORLD!", helloWorld);
    }

    @Test
    void helloworld_3_async_calls() {
        //given

        //when
        String helloWorld = cfhw.helloworld_3_async_calls();

        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", helloWorld);
    }

    @Test
    void helloworld_4_async_calls() {
        //given

        //when
        String helloWorld = cfhw.helloworld_4_async_calls();

        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE! BYE!", helloWorld);
    }
}