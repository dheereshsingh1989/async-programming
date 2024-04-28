package com.dheeresh.completableasyncpractice.async;


import com.dheeresh.completableasyncpractice.entity.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RunAsyncDemo {

    public Void saveEmployees(File jsonFile) throws ExecutionException, InterruptedException {

        ObjectMapper objectMapper = new ObjectMapper();
        Executor executor = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            try {
                List<Employee> employees = objectMapper.readValue(jsonFile, new TypeReference<List<Employee>>() {});
                System.out.println("ThreadName : " + Thread.currentThread().getName());
                System.out.println(employees.size());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executor);
        return runAsyncFuture.get();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        RunAsyncDemo runAsyncDemo = new RunAsyncDemo();
        runAsyncDemo.saveEmployees(new File("employees.json"));

    }

}
