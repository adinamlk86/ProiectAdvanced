package com.sda.java.coffeemachine.coffeemachine;

import com.sda.java.coffeemachine.menu.Ingredient;
import com.sda.java.coffeemachine.Stock;
import com.sda.java.coffeemachine.customexceptions.NotEnoughIngredientsException;
import com.sda.java.coffeemachine.menu.Coffee;
import com.sda.java.coffeemachine.menu.Espresso;
import com.sda.java.coffeemachine.menu.FilterCoffee;
import com.sda.java.coffeemachine.menu.Latte;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegularCoffeeMachine implements CoffeeMachineChoosePrepare,ServiceableCoffeeMachine {
    private final List<CoffeeMachineUse> coffeeLog = new ArrayList<>();

    private Stock stock = new Stock();
    private CoffeeType coffeeType = CoffeeType.FILTERCOFFEE;

    public RegularCoffeeMachine() {

    }

    public void chooseCoffeeType(CoffeeType coffeeType) {
        //TODO add check if credit is sufficient and show message
        this.coffeeType = coffeeType;
    }

    //detachable stock: can retrieve the stock and operate it independently
    public Stock getStock() {
        return stock;
    }

    public Coffee prepareCoffee() throws Exception {
        validateStock();
        stock.removeIngredientFromStock(Ingredient.BEANS, coffeeType.getBeansRequired());
        stock.removeIngredientFromStock(Ingredient.WATER, coffeeType.getWaterRequired());
        stock.removeIngredientFromStock(Ingredient.SUGAR, coffeeType.getSugarRequired());
        stock.removeIngredientFromStock(Ingredient.MILK, coffeeType.getMilkRequired());

        Coffee coffee = createCoffee();
        coffeeLog.add(new CoffeeMachineUse(coffee, new Date()));

        return coffee;
    }

    private void validateStock() throws Exception {
        if (!(stock.getIngredientStock(Ingredient.BEANS) >= coffeeType.getBeansRequired())) {
            throw new NotEnoughIngredientsException(coffeeType, Ingredient.BEANS);
        }
        if (!(stock.getIngredientStock(Ingredient.WATER) >= coffeeType.getWaterRequired())) {
            throw new NotEnoughIngredientsException(coffeeType, Ingredient.WATER);
        }
        if (!(stock.getIngredientStock(Ingredient.SUGAR) > coffeeType.getSugarRequired())) {
            throw new NotEnoughIngredientsException(coffeeType, Ingredient.SUGAR);
        }
        if (!(stock.getIngredientStock(Ingredient.MILK) > coffeeType.getMilkRequired())) {
            throw new NotEnoughIngredientsException(coffeeType, Ingredient.MILK);
        }
    }


    private Coffee createCoffee() {
        switch (coffeeType) {
            case LATTE:
                return new Latte();
            case ESPRESSO:
                return new Espresso();
            case FILTERCOFFEE:
                return new FilterCoffee();
            default:
                return new FilterCoffee();
        }
    }


    public String showLog() {
        StringBuilder stringBuilder = new StringBuilder();

        coffeeLog.forEach(logEntry -> stringBuilder.append(logEntry).append(System.lineSeparator()));

        return stringBuilder.toString();
    }


}
