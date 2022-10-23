package com.example.petsrore.service.impl;

import com.example.petsrore.dao.OrderDao;
import com.example.petsrore.model.Activity;
import com.example.petsrore.model.Order;
import com.example.petsrore.model.Pet;
import com.example.petsrore.service.IPetService;

import java.util.List;

public class OrderService implements com.example.petsrore.service.IOrderService {
    OrderDao orderDao = new OrderDao();
    List<Order> orderList = orderDao.load();

    IPetService petService = PetService.getInstance();

    static OrderService orderService = new OrderService();

    private OrderService() {
    }

    public static OrderService getInstance() {
        return orderService;
    }

    @Override
    public void newOrder(int orderId, String orderName, int petId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderName(orderName);
        order.setPetId(petId);
        order.setAmount(amount(petId));
        orderList.add(order);
        orderDao.save(orderList);
    }

    @Override
    public double amount(int petId){
        double amount = 0;
        List<Pet> petList = petService.getPetList();
        List<Activity> activityList = ActService.getInstance().activityList;
        for(Pet pet : petList){
            if(pet.getId() == petId && pet.getStatus() == 0){
                for(Activity activity : activityList){
                    if(activity.getActPetType() == pet.getType() && activity.getActStatus() ==0){
                        amount = pet.getPrice() * activity.getActRebate();
                    }else if(activity.getActPetType() != pet.getType() || activity.getActStatus() !=0){
                        amount = pet.getPrice();
                    }
                }
            }
        }
        return amount;
    }


    @Override
    public List<Order> getOrderList() {
        return orderList;
    }
}
