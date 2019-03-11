package com.sda.java.coffeemachine.coffeemachine;

import com.sda.java.coffeemachine.menu.Coffee;

import java.util.Date;

public class CoffeeMachineUse {

    private final Coffee preparedCoffee;
    private final Date preparationDate;

    public CoffeeMachineUse(Coffee preparedCoffee, Date preparationDate) {
        this.preparedCoffee = preparedCoffee;
        this.preparationDate = preparationDate;
    }

    @Override
    public String toString() {
        return "CoffeeMachineUse: " + preparedCoffee +
                ", " + preparationDate;
    }
}
