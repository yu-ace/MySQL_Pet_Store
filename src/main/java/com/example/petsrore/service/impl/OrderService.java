package com.example.petsrore.service.impl;

import com.example.petsrore.model.Activity;
import com.example.petsrore.model.Order;
import com.example.petsrore.model.Pet;
import com.example.petsrore.service.IActService;
import com.example.petsrore.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements com.example.petsrore.service.IOrderService {
    @Autowired
    private IPetService petService;
    @Autowired
    private IActService actService;

    @Override
    public void newOrder(String orderName, int petId) {
        try {
            String tmp = "INSERT INTO `order` (name,pet_id,amount) value ('%s',%d,%.2f);";
            String sqlStr = String.format(tmp,orderName,petId,amount(petId));
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://192.168.50.252:3306/pet_store", "root", "123456");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double amount(int petId){
        double amount = 0;
        List<Pet> petList = petService.getPetList();
        List<Activity> activityList = actService.getActivityList();
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
        List<Order> orderList = new ArrayList<>();
        try {
            String sqlStr = "SELECT * FROM `order`;";
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://192.168.50.252:3306/pet_store", "root", "123456");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int petId = resultSet.getInt("pet_id");
                double amount = resultSet.getDouble("amount");
                Order order = new Order();
                order.setOrderId(id);
                order.setOrderName(name);
                order.setPetId(petId);
                order.setAmount(amount);
                orderList.add(order);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
