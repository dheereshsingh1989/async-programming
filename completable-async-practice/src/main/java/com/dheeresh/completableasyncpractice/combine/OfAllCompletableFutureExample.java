package com.dheeresh.completableasyncpractice.combine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OfAllCompletableFutureExample {

    public static void joinCompletableFuture() throws ExecutionException, InterruptedException {

        // Create two CompletableFutures
        CompletableFuture<String> response1 = CompletableFuture.supplyAsync(() -> "API 1 response");
        CompletableFuture<String> response2 = CompletableFuture.supplyAsync(() -> "API 2 response");

        // Join the two CompletableFutures
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(response1, response2);

        // Wait for both API calls to complete
        combinedFuture.join();

        // Get the results of the two API calls
        String api1Response = response1.get();
        String api2Response = response2.get();

        // Print the results
        System.out.println("API 1 response: " + api1Response);
        System.out.println("API 2 response: " + api2Response);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        OfAllCompletableFutureExample.joinCompletableFuture();
    }
}
