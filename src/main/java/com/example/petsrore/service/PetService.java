package com.example.petsrore.service;

import com.example.petsrore.dao.PetDao;
import com.example.petsrore.model.Pet;

import java.util.List;

public class PetService {
    PetDao petDao = new PetDao();
    List<Pet> petList = petDao.load();

    public void newPet(int id,String name,int type,double price){
        Pet pet = new Pet();
        pet.setId(id);
        pet.setName(name);
        pet.setType(type);
        pet.setStatus(0);
        pet.setPrice(price);
        petList.add(pet);
        petDao.save(petList);
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public double[] ave(){
        double[] sum = new double[2];
        int[] num = new int[2];
        double[] ave = new double[2];
        for(Pet pet :petList){
            if(pet.getType() == 0){
                sum[0] = sum[0] + pet.getPrice();
                num[0]++;
            }else{
                sum[1] = sum[1] + pet.getPrice();
                num[1]++;
            }
        }
        for(int i = 0;i < 2;i++){
            ave[i] = sum[i] / num[i];
        }
        return ave;
    }

    public Pet[] max(){
        Pet[] pets = new Pet[2];
        for(Pet pet : petList){
            if(pet.getType() == 0){
                if(pets[0] == null){
                    pets[0] = pet;
                }else if(pets[0].getPrice() < pet.getPrice()){
                    pets[0] = pet;
                }
            }else{
                if(pets[1] == null){
                    pets[1] = pet;
                }else if(pets[1].getPrice() < pet.getPrice()){
                    pets[1] = pet;
                }
            }
        }
        return pets;
    }

    public Pet[] min(){
        Pet[] pets = new Pet[2];
        for(Pet pet : petList){
            if(pet.getType() == 0){
                if(pets[0] == null){
                    pets[0] = pet;
                }else if(pets[0].getPrice() > pet.getPrice()){
                    pets[0] = pet;
                }
            }else{
                if(pets[1] == null){
                    pets[1] = pet;
                }else if(pets[1].getPrice() > pet.getPrice()){
                    pets[1] = pet;
                }
            }
        }
        return pets;
    }
}
