package com.dheeresh.asyncecommapp.controller;

import com.dheeresh.asyncecommapp.entity.Order;
import com.dheeresh.asyncecommapp.service.OrderFulfillmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderFulfillmentController {

    @Autowired
    private OrderFulfillmentService service;

    @PostMapping
    public ResponseEntity<Order> processOrder(@RequestBody Order order) throws InterruptedException {
        service.processOrder(order);
        service.notifyUser(order);
        service.assignVendor(order);
        service.productPackaging(order);
        service.assignDeliveryPartner(order);
        service.dispatch(order);
        return ResponseEntity.ok(order);
    }
}
