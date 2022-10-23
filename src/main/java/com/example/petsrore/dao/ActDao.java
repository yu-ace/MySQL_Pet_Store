package com.example.petsrore.dao;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSONUtil;
import com.example.petsrore.model.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActDao {


    public void save(List<Activity> activityList){
        String str = JSONUtil.toJsonStr(activityList);
        FileWriter fileWriter = new FileWriter(FileUtil.getUserHomePath() + "/ptt/activity.dat");
        fileWriter.write(str);
    }

    public List<Activity> load(){
        try{
            FileReader fileReader = new FileReader(FileUtil.getUserHomePath() + "/ptt/activity.dat");
            String str = fileReader.readString();
            List<Activity> activityList = JSONUtil.toList(str, Activity.class);
            return activityList;
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
}
