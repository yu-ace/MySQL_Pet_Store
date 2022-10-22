package com.example.petsrore.service;

import com.example.petsrore.dao.OrderDao;
import com.example.petsrore.model.Activity;
import com.example.petsrore.model.Order;
import com.example.petsrore.model.Pet;

import java.util.List;

public class OrderService {
    OrderDao orderDao = new OrderDao();
    List<Order> orderList = orderDao.load();

    static OrderService orderService = new OrderService();
    private OrderService(){
    }
    public static OrderService getInstance(){
        return orderService;
    }

    public void newOrder(int orderId,String orderName,int petId,double amount){
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderName(orderName);
        order.setPetId(petId);
        order.setAmount(amount);
        orderList.add(order);
        orderDao.save(orderList);
    }

    public double actAmount(int petId){
        double amount = 0;
        List<Pet> petList = PetService.getInstance().petList;
        List<Activity> activityList = ActService.getInstance().activityList;
        for(Pet pet : petList){
            if(pet.getId() == petId && pet.getStatus() == 0){
                for(Activity activity : activityList){
                    if(activity.getActPetType() == pet.getType() && activity.getActStatus() ==0){
                        amount = pet.getPrice() * activity.getActRebate();
                    }
                }
            }
        }
        return amount;
    }

    public double amount(int petId){
        double amount = 0;
        List<Pet> petList = PetService.getInstance().petList;
        List<Activity> activityList = ActService.getInstance().activityList;
        for(Pet pet : petList){
            if(pet.getId() == petId && pet.getStatus() == 0){
                for(Activity activity : activityList){
                    if(activity.getActPetType() != pet.getType() || activity.getActStatus() !=0){
                        amount = pet.getPrice();
                    }
                }
            }
        }
        return amount;
    }

    public List<Order> getOrderList() {
        return orderList;
    }
}
