package com.example.petsrore.service;

import com.example.petsrore.model.Activity;

import java.util.List;

public interface IActService {
    void newAct(String actName, double actRebate, int actPetType);

    List<Activity> getActivityList();
}
