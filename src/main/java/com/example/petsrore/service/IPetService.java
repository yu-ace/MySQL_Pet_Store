package com.example.petsrore.service;

import com.example.petsrore.model.Pet;

import java.util.List;

public interface IPetService {
    void newPet(int id, String name, int type, double price);

    List<Pet> getPetList();

    double[] ave();

    Pet[] max();

    Pet[] min();

    void changeStatus(int petId, int status);
}
