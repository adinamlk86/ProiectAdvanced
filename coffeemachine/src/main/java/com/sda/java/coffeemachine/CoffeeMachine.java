package com.sda.java.coffeemachine;

import com.sda.java.coffeemachine.menu.Coffee;
import com.sda.java.coffeemachine.menu.Espresso;

public class CoffeeMachine {

    private int coffeeStock = 10;

    //prepareCoffee

    public Coffee prepareCoffee(CoffeeType coffeeType) throws Exception{
        if(coffeeStock > coffeeType.getCoffeeRequired()){
            coffeeStock -= coffeeType.getCoffeeRequired();
            return new Espresso();
        } else
        {
            throw new Exception("Cannot make coffee");
        }
    }
    //addCredit
    //returnCredit

    public CoffeeMachine() {

    }

    public static void main(String[] args) {
        final CoffeeMachine coffeeMachine = new CoffeeMachine();

    }
}
