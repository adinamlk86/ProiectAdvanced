package com.sda.java.coffeemachine.customexceptions;

import com.sda.java.coffeemachine.coffeemachine.CoffeeType;
import com.sda.java.coffeemachine.menu.Ingredient;

public class NotEnoughIngredientsException extends Exception {

    public NotEnoughIngredientsException(CoffeeType coffeeType, Ingredient ingredient){
        super("Not enough " + ingredient + " for the "+ coffeeType);
    }
}
