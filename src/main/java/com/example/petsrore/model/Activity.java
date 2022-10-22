package com.example.petsrore.model;

public class Activity {
    int actId;
    String actName;
    double actRebate;
    /**
     * 0猫
     * 1狗
     * 2所有
     */
    int actPetType;
    /**
     * 0开启
     * 1关闭
     */
    int actStatus;

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public double getActRebate() {
        return actRebate;
    }

    public void setActRebate(double actRebate) {
        this.actRebate = actRebate;
    }

    public int getActPetType() {
        return actPetType;
    }

    public void setActPetType(int actPetType) {
        this.actPetType = actPetType;
    }

    public int getActStatus() {
        return actStatus;
    }

    public void setActStatus(int actStatus) {
        this.actStatus = actStatus;
    }
}
