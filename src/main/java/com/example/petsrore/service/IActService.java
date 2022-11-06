package com.example.petsrore.service;

import com.example.petsrore.model.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IActService {
    void newAct(String actName, double actRebate, int actPetType);

    List<Activity> getActivityList();
}
