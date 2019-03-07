package com.sda.java.coffeemachine;

import com.sda.java.coffeemachine.menu.Coffee;
import com.sda.java.coffeemachine.menu.Espresso;
import com.sda.java.coffeemachine.menu.FilterCoffee;
import com.sda.java.coffeemachine.menu.Latte;

public class CoffeeMachine {

    private Stock stock = new Stock();
    private CoffeeType coffeeType = CoffeeType.FILTERCOFFEE;


    public static void main(String[] args) throws Exception {
        final CoffeeMachine coffeeMachine = new CoffeeMachine();
        Stock stock = coffeeMachine.getStock();
        stock.getIngredientStock(Ingredient.BEANS);
        stock.addIngredientToStock(Ingredient.BEANS, 500);
        stock.addIngredientToStock(Ingredient.WATER, 1000);
        stock.addIngredientToStock(Ingredient.SUGAR, 500);
        stock.addIngredientToStock(Ingredient.MILK, 500);
        //user selects coffee type
        coffeeMachine.chooseCoffeeType(CoffeeType.LATTE);

        //user presses start
        final Coffee coffee = coffeeMachine.prepareCoffee();
        System.out.println("Drinking the " + coffee);

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
        if (stock.getIngredientStock(Ingredient.BEANS) > coffeeType.getBeansRequired() &&
                stock.getIngredientStock(Ingredient.WATER) > coffeeType.getWaterRequired() &&
                stock.getIngredientStock(Ingredient.SUGAR) > coffeeType.getSugarRequired() &&
                stock.getIngredientStock(Ingredient.MILK) > coffeeType.getMilkRequired()) {
            stock.removeIngredientFromStock(Ingredient.BEANS,coffeeType.getBeansRequired());
            stock.removeIngredientFromStock(Ingredient.WATER,coffeeType.getWaterRequired());
            stock.removeIngredientFromStock(Ingredient.SUGAR,coffeeType.getSugarRequired());
            stock.removeIngredientFromStock(Ingredient.MILK,coffeeType.getMilkRequired());
            return createCoffee();
        }
        throw new Exception("Cannot make coffee");

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
    //addCredit
    //returnCredit

    public CoffeeMachine() {

    }
}
