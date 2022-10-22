package com.example.petsrore.service;

import com.example.petsrore.dao.OrderDao;
import com.example.petsrore.model.Order;

import java.util.List;

public class OrderService {
    static List<Order> orderList = OrderDao.load();

    public static void newOrder(int orderId,String orderName,int petId,double amount){
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderName(orderName);
        order.setPetId(petId);
        order.setAmount(amount);
        orderList.add(order);
        OrderDao.save(orderList);
    }

    public static List<Order> getOrderList() {
        return orderList;
    }
}
