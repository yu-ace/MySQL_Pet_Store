package com.example.petsrore.service;

import com.example.petsrore.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService {
    void newOrder(String orderName, int petId);

    double amount(int petId);

    List<Order> getOrderList();
}
