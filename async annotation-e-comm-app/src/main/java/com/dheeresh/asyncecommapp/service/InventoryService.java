package com.dheeresh.asyncecommapp.service;

import com.dheeresh.asyncecommapp.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryService {

    final static Logger logger = LoggerFactory.getLogger(InventoryService.class);

    public boolean checkProductAvailability(Order order){
        return true;
    }


}
