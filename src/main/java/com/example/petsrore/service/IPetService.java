package com.example.petsrore.service;

import com.example.petsrore.model.Pet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPetService {
    void newPet(String name, int type, double price);

    List<Pet> getPetList();

    double[] ave();

    Pet[] max();

    Pet[] min();

    void changeStatus(int petId, int status);
}
