package com.example.petsrore.model;

import org.springframework.stereotype.Service;

@Service
public class Pet {
    private int id;
    private String name;
    /**
     * 0猫
     * 1狗
     */
    private int type;
    private double price;
    /**
     * 0待售
     * 1售罄
     */
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
