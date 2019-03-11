package com.sda.java.coffeemachine;

import com.sda.java.coffeemachine.customexceptions.NotEnoughIngredientsException;
import com.sda.java.coffeemachine.menu.Coffee;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        final RegularCoffeeMachine coffeeMachine = new RegularCoffeeMachine();

        CoffeeMachineChoosePrepare coffeeMachineChoosePrepare = coffeeMachine;
        ServiceableCoffeeMachine serviceableCoffeeMachine = coffeeMachine;

        Stock stock = serviceableCoffeeMachine.getStock();

        stock.addIngredientToStock(Ingredient.BEANS, 500);
        stock.addIngredientToStock(Ingredient.WATER, 1000);
        stock.addIngredientToStock(Ingredient.SUGAR, 500);
        stock.addIngredientToStock(Ingredient.MILK, 500);
        //user selects coffee type

        coffeeMachineChoosePrepare.chooseCoffeeType(CoffeeType.ESPRESSO);
        //user presses start
        try {
            final Coffee coffee = coffeeMachineChoosePrepare.prepareCoffee();
            System.out.println("Drinking the " + coffee);
        } catch (NotEnoughIngredientsException e) {
            System.out.println(e.getMessage());
        }

        //latte
        coffeeMachineChoosePrepare.chooseCoffeeType(CoffeeType.LATTE);
        try {
            final Coffee coffee2 = coffeeMachineChoosePrepare.prepareCoffee();
            System.out.println("Drinking the " + coffee2);
        }catch (NotEnoughIngredientsException e) {
            System.out.println(e.getMessage());
        }
        //filtered
        coffeeMachineChoosePrepare.chooseCoffeeType(CoffeeType.FILTERCOFFEE);
        try {
            final Coffee coffee3 = coffeeMachineChoosePrepare.prepareCoffee();
            System.out.println("Drinking the " + coffee3);
        }catch (NotEnoughIngredientsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Remaining ingredients:\n"+stock.toString());

        String myLog = serviceableCoffeeMachine.showLog();
        System.out.println("Coffee history:\n"+myLog);

        Files.write(Paths.get("C:\\Users\\LENOVO\\IdeaProjects\\GIT\\coffeemachine\\History.txt"),myLog.getBytes());


    }
}
