package com.example.petsrore.service;

import com.example.petsrore.dao.ActDao;
import com.example.petsrore.model.Activity;

import java.util.List;

public class ActService {
    List<Activity> activityList = new ActDao().load();

    public void newAct(int actId,String actName,double actRebate,int actPetType){
        Activity activity = new Activity();
        activity.setActId(actId);
        activity.setActName(actName);
        activity.setActRebate(actRebate);
        activity.setActStatus(0);
        activity.setActPetType(actPetType);
        activityList.add(activity);
        new ActDao().save(activityList);
    }

    public List<Activity> getActivityList() {
        return activityList;
    }
}
