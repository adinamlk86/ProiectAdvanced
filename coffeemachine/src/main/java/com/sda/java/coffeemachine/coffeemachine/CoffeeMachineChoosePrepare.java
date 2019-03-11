package com.sda.java.coffeemachine.coffeemachine;

import com.sda.java.coffeemachine.menu.Coffee;

public interface CoffeeMachineChoosePrepare {

     void chooseCoffeeType(CoffeeType coffeeType);

     Coffee prepareCoffee() throws Exception;
    
}
