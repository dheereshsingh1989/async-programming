package com.dheeresh.completableasyncpractice.combine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class NonBlockingTest {

    public static int compute(int number){
       // sleep(2);
        return number * 2;
    }


    public static void main(String[] args) {

        System.out.println("Start");
        CompletableFuture.supplyAsync(() -> compute(10))
                        .thenAccept(System.out::println);
        System.out.println("End");
        //sleep(3);


    }

    public static void sleep(long t){
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
