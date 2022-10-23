package com.example.petsrore.service.impl;

import com.example.petsrore.model.Pet;
import com.example.petsrore.service.IPetService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetService implements IPetService {
    private static PetService petService = new PetService();

    private PetService() {
    }

    public static PetService getInstance() {
        return petService;
    }

    public void newPet(int id, String name, int type, double price) {
        try {
            String tmp = "INSERT INTO pet (name,type,price) value ('%s',%d,%.2f);";
            String sqlStr = String.format(tmp, name, type, price);
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://192.168.50.252:3306/pet_store", "root", "123456");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            preparedStatement.execute();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Pet> getPetList() {
        List<Pet> petList = new ArrayList<>();
        try {
            String sqlStr = "SELECT * FROM pet;";
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://192.168.50.252:3306/pet_store", "root", "123456");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int type = resultSet.getInt("type");
                double price = resultSet.getDouble("price");
                int status = resultSet.getInt("status");
                Pet pet = new Pet();
                pet.setId(id);
                pet.setStatus(status);
                pet.setName(name);
                pet.setPrice(price);
                pet.setType(type);
                petList.add(pet);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return petList;
    }

    public double[] ave() {
        double[] res = new double[2];
        try {
            String sqlStr = "SELECT type,avg(prive) FROM pet group by type order by type;";
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://192.168.50.252:3306/pet_store", "root", "123456");
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            res[0] = resultSet.getDouble("a");
            resultSet.next();
            res[1] = resultSet.getDouble("a");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Pet[] max() {
        String tmp = "SELECT distinct * from pet where type = %d order by price desc LIMIT 1;";
        Pet[] pets = new Pet[2];
        for (int i = 0; i < 2; i++) {
            try {
                String sqlStr = String.format(tmp, i);
                Connection connection = DriverManager
                        .getConnection("jdbc:mysql://192.168.50.252:3306/pet_store", "root", "123456");
                PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int type = resultSet.getInt("type");
                    double price = resultSet.getDouble("price");
                    int status = resultSet.getInt("status");
                    Pet pet = new Pet();
                    pet.setId(id);
                    pet.setStatus(status);
                    pet.setName(name);
                    pet.setPrice(price);
                    pet.setType(type);
                    pets[i] = pet;
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pets;
    }

    public Pet[] min() {
        String tmp = "SELECT distinct * from pet where type = %d order by price asc LIMIT 1;";
        Pet[] pets = new Pet[2];
        for (int i = 0; i < 2; i++) {
            try {
                String sqlStr = String.format(tmp, i);
                Connection connection = DriverManager
                        .getConnection("jdbc:mysql://192.168.50.252:3306/pet_store", "root", "123456");
                PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int type = resultSet.getInt("type");
                    double price = resultSet.getDouble("price");
                    int status = resultSet.getInt("status");
                    Pet pet = new Pet();
                    pet.setId(id);
                    pet.setStatus(status);
                    pet.setName(name);
                    pet.setPrice(price);
                    pet.setType(type);
                    pets[i] = pet;
                }
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pets;
    }

    public void changeStatus(int petId, int status) {
        String tmp = "UPDATE pet SET status = %d WHERE petId = %d";
        for(Pet pet : petService.getPetList()){
            try {
                String sqlStr = String.format(tmp,petId,status);
                Connection connection =DriverManager
                        .getConnection("jdbc:mysql://192.168.50.252:3306/pet_store", "root", "123456");
                PreparedStatement preparedStatement = connection.prepareStatement(sqlStr);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    pet.setStatus(status);
                }
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
