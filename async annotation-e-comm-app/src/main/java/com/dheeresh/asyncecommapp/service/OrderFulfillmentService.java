package com.dheeresh.asyncecommapp.service;

import com.dheeresh.asyncecommapp.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderFulfillmentService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PaymentService paymentService;

    final static Logger logger = LoggerFactory.getLogger(OrderFulfillmentService.class);

    public Order processOrder(Order order) throws InterruptedException {
        if(inventoryService.checkProductAvailability(order)){
            order.setTrackingId(UUID.randomUUID().toString());
            paymentService.makePayment(order);
        }else{
            throw new RuntimeException();
        }
        return order;
    }

    @Async("asyncTaskExecutor")
    public void notifyUser(Order order) throws InterruptedException {
        Thread.sleep(1000);
        logger.info("Notified to the user : " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void assignVendor(Order order) throws InterruptedException {
        Thread.sleep(2000L);
        logger.info("Product assigned to vendor: " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void productPackaging(Order order) throws InterruptedException {
        Thread.sleep(2000L);
        logger.info("product packaging done: " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void assignDeliveryPartner(Order order) throws InterruptedException {
        Thread.sleep(2000L);
        logger.info("Product assigned to deliver partner: " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void dispatch(Order order) throws InterruptedException {
        Thread.sleep(2000L);
        logger.info("Product has been dispatched: " + Thread.currentThread().getName());
    }
}
