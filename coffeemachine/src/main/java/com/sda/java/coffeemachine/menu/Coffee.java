package com.sda.java.coffeemachine.menu;

import com.sda.java.coffeemachine.CoffeeType;

public abstract class Coffee {

    private final int coffee;
    private final int water;
    private final int sugar;
    private final int milk;

    public Coffee(CoffeeType coffeeType) {
        this.coffee = coffeeType.getCoffeeRequired();
        this.water = coffeeType.getWaterRequired();
        this.sugar = coffeeType.getSugarRequired();
        this.milk = coffeeType.getMilkRequired();
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "coffee=" + coffee +
                ", water=" + water +
                ", sugar=" + sugar +
                ", milk=" + milk +
                '}';
    }
}
