package com.dheeresh.completableasyncpractice.async;

import com.dheeresh.completableasyncpractice.database.EmployeeDatabase;
import com.dheeresh.completableasyncpractice.entity.Employee;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SupplyAsyncDemo {

    public List<Employee> getEmployees() throws ExecutionException, InterruptedException {

        Executor executor = Executors.newCachedThreadPool();
        CompletableFuture<List<Employee>> supplyAsynk = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Thread : " + Thread.currentThread().getName());
                return  EmployeeDatabase.fetchEmployees();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }, executor);
        return supplyAsynk.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        SupplyAsyncDemo supplyAsyncDemo = new SupplyAsyncDemo();
        List<Employee> employees = supplyAsyncDemo.getEmployees();
        System.out.println((long) employees.size());
        employees.forEach(System.out::println);
    }
}
