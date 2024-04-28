package com.dheeresh.completableasyncpractice.async;

import com.dheeresh.completableasyncpractice.database.EmployeeDatabase;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class EmployeeReminderService {
    public CompletableFuture<Void> sendReminderToEmployees() throws ExecutionException, InterruptedException {

        Executor executor = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("My Thread: " + Thread.currentThread().getName());
            try {
                return EmployeeDatabase.fetchEmployees();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, executor).thenApplyAsync((employees -> {
            System.out.println("Getting new joiners: " + Thread.currentThread().getName());
            return employees.stream()
                    .filter(emp -> "true".equalsIgnoreCase(emp.getNewJoiner()))
                    .collect(Collectors.toList());
        }), executor).thenApplyAsync(employees -> {
            System.out.println("Getting training status: " + Thread.currentThread().getName());
            return employees.stream()
                    .filter(emp -> "true".equalsIgnoreCase(emp.getLearningPending()))
                    .collect(Collectors.toList());
        }, executor).thenApplyAsync(employees -> {
            System.out.println("Getting emailId: " + Thread.currentThread().getName());
            return employees.stream()
                    .map(emp -> emp.getEmail())
                    .collect(Collectors.toList());
        }, executor).thenAcceptAsync((emails) ->{
            System.out.println("Sending reminder : " + Thread.currentThread().getName());
            emails.forEach(EmployeeReminderService::sendEmailReminder);
        }, executor);
        return completableFuture;
    }

    public static void sendEmailReminder(String emailId){
        System.out.println("sending reminder email to : " + emailId);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        EmployeeReminderService service = new EmployeeReminderService();
        service.sendReminderToEmployees().get();
    }
}
