package com.dheeresh.completableasyncpractice.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest {

    public void testCompletableFuture() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> "hello")
                .thenApply(String::toUpperCase)
                .thenAccept(System.out::println)
                .thenRun(() -> System.out.println("Completed the job"));

        completableFuture.get();
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFutureTest test = new CompletableFutureTest();
        test.testCompletableFuture();
    }
}
