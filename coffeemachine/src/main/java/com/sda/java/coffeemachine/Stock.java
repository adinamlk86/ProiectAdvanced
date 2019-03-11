package com.sda.java.coffeemachine;

import java.util.HashMap;
import java.util.Map;

public class Stock {

    private Map<Ingredient, Integer> ingredients = new HashMap<>();

    public Stock() {
        for(Ingredient eachIngredient : Ingredient.values()) {
            ingredients.put(eachIngredient, 0);
        }
    }

    public int getIngredientStock(Ingredient ingredient) throws Exception{
        if(!ingredients.containsKey(ingredient)){
            throw  new Exception("404 Ingredient not found: " + ingredient);
        }
        else{
            return ingredients.get(ingredient);
        }
    }

    public void removeIngredientFromStock(Ingredient ingredient, int quantityToBeRemoved) throws Exception{
        if(!ingredients.containsKey(ingredient)){
            throw new Exception("404 Ingredient not found: "+ ingredient);
        }

            int currentQuantity = ingredients.get(ingredient);

            if (currentQuantity < quantityToBeRemoved) {
                throw new Exception("Not enough "+ingredient+ " in stock.");
            }
            int updatedQuantity = currentQuantity - quantityToBeRemoved;
            ingredients.put(ingredient, updatedQuantity);


    }

    public void addIngredientToStock(Ingredient ingredient, int quantityToBeAdded)throws Exception{
        if(!ingredients.containsKey(ingredient)){
            throw new Exception("404 Ingredient not found: "+ ingredient);
        }
            int currentQuantity = ingredients.get(ingredient);
            int updatedQuantity = currentQuantity + quantityToBeAdded;
            ingredients.put(ingredient, updatedQuantity);

    }

    @Override
    public String toString() {
        return "Stock{" +
                "ingredients=" + ingredients +
                '}';
    }
}
