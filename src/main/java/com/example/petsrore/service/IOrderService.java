package com.example.petsrore.service;

import com.example.petsrore.model.Order;

import java.util.List;

public interface IOrderService {
    void newOrder(int orderId, String orderName, int petId);

    double amount(int petId);

    List<Order> getOrderList();
}
