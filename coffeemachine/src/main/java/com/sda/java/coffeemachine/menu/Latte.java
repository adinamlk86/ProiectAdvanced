package com.sda.java.coffeemachine.menu;

import com.sda.java.coffeemachine.coffeemachine.CoffeeType;

public class Latte extends Coffee {


    public static final CoffeeType LATTE = CoffeeType.LATTE;

    public Latte() {
        super(LATTE);
    }

    protected String getCoffeeName() {
        return LATTE.toString();
    }
}
