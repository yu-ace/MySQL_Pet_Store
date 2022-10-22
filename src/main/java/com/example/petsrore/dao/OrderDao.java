package com.example.petsrore.dao;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSONUtil;
import com.example.petsrore.model.Order;
import com.example.petsrore.model.Pet;

import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public void save(List<Order> orderList){
        String str = JSONUtil.toJsonStr(orderList);
        FileWriter fileWriter = new FileWriter(FileUtil.getUserHomePath() + "/ptt/order.dat");
        fileWriter.write(str);
    }

    public List<Order> load(){
        try{
            FileReader fileReader = new FileReader(FileUtil.getUserHomePath() + "/ptt/order.dat");
            String str = fileReader.readString();
            List<Order> orderList = JSONUtil.toList(str, Order.class);
            return orderList;
        }catch(Exception e){
            return new ArrayList<>();
        }
    }
}
