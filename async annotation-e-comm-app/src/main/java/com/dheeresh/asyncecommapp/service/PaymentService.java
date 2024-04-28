package com.dheeresh.asyncecommapp.service;

import com.dheeresh.asyncecommapp.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {
    final static Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public void makePayment(Order order) throws InterruptedException {
        logger.info("initiate payment for order : " + order.getProductId());
        Thread.sleep(2000L);
        logger.info("payment completed for order : " + order.getProductId());
    }

}
