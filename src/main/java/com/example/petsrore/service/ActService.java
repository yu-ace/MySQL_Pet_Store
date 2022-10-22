package com.example.petsrore.service;

import com.example.petsrore.dao.ActDao;
import com.example.petsrore.model.Activity;

import java.util.List;

public class ActService {
    static List<Activity> activityList = ActDao.load();

    public static void newAct(int actId,String actName,double actRebate,int actPetType){
        Activity activity = new Activity();
        activity.setActId(actId);
        activity.setActName(actName);
        activity.setActRebate(actRebate);
        activity.setActStatus(0);
        activity.setActPetType(actPetType);
        activityList.add(activity);
        ActDao.save(activityList);
    }

    public static List<Activity> getActivityList() {
        return activityList;
    }
}
