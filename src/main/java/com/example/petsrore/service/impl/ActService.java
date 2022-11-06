package com.example.petsrore.service.impl;

import com.example.petsrore.model.Activity;
import com.example.petsrore.service.IActService;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class ActService implements IActService {

    @Override
    public void newAct(String actName, double actRebate, int actPetType) {
        try {
            String tmp = "INSERT INTO activity (name,rebate,pet_type) value ('%s',%.2f,%d);";
            String sqlStr = String.format(tmp,actName,actRebate,actPetType);
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
    public List<Activity> getActivityList() {
        List<Activity> activityList = new ArrayList<>();
        try {
            String sqlStr = "SELECT * FROM activity;";
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://192.168.50.252:3306/pet_store", "root", "123456");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double rebate = resultSet.getDouble("rebate");
                int type = resultSet.getInt("pet_type");
                int status = resultSet.getInt("status");
                Activity activity = new Activity();
                activity.setActId(id);
                activity.setActName(name);
                activity.setActRebate(rebate);
                activity.setActPetType(type);
                activity.setActStatus(status);
                activityList.add(activity);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityList;
    }
}
