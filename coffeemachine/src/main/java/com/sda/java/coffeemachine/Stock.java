package com.sda.java.coffeemachine;

public class Stock {


    private int beansStock;
    private int waterStock;
    private int sugarStock;
    private int milkStock;

    public int getBeansStock() {
        return beansStock;
    }

    public void removeBeansFromStock(int quantity){
       beansStock -= quantity;
    }

    public void addBeansToStock(int quantity){
        beansStock+=quantity;
    }

    public int getWaterStock() {
        return waterStock;
    }

    public void removeWaterFromStock(int quantity){
        waterStock-=quantity;
    }

    public void addWaterToStock(int quantity){
        waterStock+=quantity;
    }

    public int getSugarStock() {
        return sugarStock;
    }

    public void removeSugarFromStock(int quantity){
        sugarStock-= quantity;
    }

    public void addSugarToStock(int quantity){
        sugarStock+=quantity;
    }

    public int getMilkStock() {
        return milkStock;
    }

    public void removeMilkFromStock(int quantity){
        milkStock-=quantity;
    }

    public void addMilkToStock(int quantity){
        milkStock+=quantity;
    }
}
