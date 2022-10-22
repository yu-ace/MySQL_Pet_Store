package com.example.petsrore.service;

import com.example.petsrore.dao.ActDao;
import com.example.petsrore.model.Activity;

import java.util.List;

public class ActService {
    ActDao actDao = new ActDao();
    List<Activity> activityList = actDao.load();

    public void newAct(int actId,String actName,double actRebate,int actPetType){
        Activity activity = new Activity();
        activity.setActId(actId);
        activity.setActName(actName);
        activity.setActRebate(actRebate);
        activity.setActStatus(0);
        activity.setActPetType(actPetType);
        activityList.add(activity);
        actDao.save(activityList);
    }

    public List<Activity> getActivityList() {
        return activityList;
    }
}
