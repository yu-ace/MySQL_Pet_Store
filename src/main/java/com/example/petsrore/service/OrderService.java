package com.example.petsrore.service;

import com.example.petsrore.dao.ActDao;
import com.example.petsrore.dao.OrderDao;
import com.example.petsrore.dao.PetDao;
import com.example.petsrore.model.Activity;
import com.example.petsrore.model.Order;
import com.example.petsrore.model.Pet;

import java.util.List;

public class OrderService {
    OrderDao orderDao = new OrderDao();
    ActDao actDao = new ActDao();
    PetDao petDao = new PetDao();
    List<Order> orderList = orderDao.load();

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
        List<Pet> petList = petDao.load();
        List<Activity> activityList = actDao.load();
        for(Pet pet : petList){
            if(pet.getId() == petId && pet.getStatus() == 0){
                for(Activity activity : activityList){
                    if(activity.getActPetType() == pet.getType() && activity.getActStatus() ==0){
                        pet.setStatus(1);
                        petDao.save(petList);
                        amount = pet.getPrice() * activity.getActRebate();
                    }
                }
            }
        }
        return amount;
    }

    public double amount(int petId){
        double amount = 0;
        List<Pet> petList = petDao.load();
        List<Activity> activityList = actDao.load();
        for(Pet pet : petList){
            if(pet.getId() == petId && pet.getStatus() == 0){
                for(Activity activity : activityList){
                    if(activity.getActPetType() != pet.getType() || activity.getActStatus() !=0){
                        pet.setStatus(1);
                        petDao.save(petList);
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
