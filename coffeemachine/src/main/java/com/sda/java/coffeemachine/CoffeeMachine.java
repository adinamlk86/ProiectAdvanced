package com.sda.java.coffeemachine;

import com.sda.java.coffeemachine.customexceptions.NotEnoughIngredientsException;
import com.sda.java.coffeemachine.menu.Coffee;
import com.sda.java.coffeemachine.menu.Espresso;
import com.sda.java.coffeemachine.menu.FilterCoffee;
import com.sda.java.coffeemachine.menu.Latte;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CoffeeMachine {
    private final List<CoffeeMachineUse> coffeeLog = new ArrayList<>();

    private Stock stock = new Stock();
    private CoffeeType coffeeType = CoffeeType.FILTERCOFFEE;


    public static void main(String[] args) throws Exception {
        final CoffeeMachine coffeeMachine = new CoffeeMachine();
        Stock stock = coffeeMachine.getStock();

        stock.addIngredientToStock(Ingredient.BEANS, 500);
        stock.addIngredientToStock(Ingredient.WATER, 1000);
        stock.addIngredientToStock(Ingredient.SUGAR, 500);
        stock.addIngredientToStock(Ingredient.MILK, 500);
        //user selects coffee type

        coffeeMachine.chooseCoffeeType(CoffeeType.ESPRESSO);
        //user presses start
        try {
            final Coffee coffee = coffeeMachine.prepareCoffee();
            System.out.println("Drinking the " + coffee);
        } catch (NotEnoughIngredientsException e) {
            System.out.println(e.getMessage());
        }



        //latte
        coffeeMachine.chooseCoffeeType(CoffeeType.LATTE);
        try {
            final Coffee coffee2 = coffeeMachine.prepareCoffee();
            System.out.println("Drinking the " + coffee2);
        }catch (NotEnoughIngredientsException e) {
            System.out.println(e.getMessage());
        }
        //filtered
        coffeeMachine.chooseCoffeeType(CoffeeType.FILTERCOFFEE);
        try {
            final Coffee coffee3 = coffeeMachine.prepareCoffee();
            System.out.println("Drinking the " + coffee3);
        }catch (NotEnoughIngredientsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Remaining ingredients: "+stock.toString());

        String myLog = coffeeMachine.showLog();
        System.out.println("Coffee history: "+myLog);

        Files.write(Paths.get("C:\\Users\\LENOVO\\IdeaProjects\\GIT\\coffeemachine\\History.txt"),myLog.getBytes());

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
        coffeeLog.add(new CoffeeMachineUse(coffee,new Date()));

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
    public CoffeeMachine() {

    }

    private String showLog(){
        StringBuilder stringBuilder = new StringBuilder();

        coffeeLog.forEach(logEntry -> stringBuilder.append(logEntry).append(System.lineSeparator()));

        return  stringBuilder.toString();
    }


}
