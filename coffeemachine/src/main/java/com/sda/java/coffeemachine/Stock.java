package com.sda.java.coffeemachine;

import com.sda.java.coffeemachine.customexceptions.IngredientNotFoundError;
import com.sda.java.coffeemachine.menu.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class Stock {

    private Map<Ingredient, Integer> ingredients = new HashMap<>();

    public Stock() {
        for(Ingredient eachIngredient : Ingredient.values()) {
            ingredients.put(eachIngredient, 0);
        }
    }

    public int getIngredientStock(Ingredient ingredient){
        if(!ingredients.containsKey(ingredient)){
            throw  new IngredientNotFoundError(ingredient);
        }
        else{
            return ingredients.get(ingredient);
        }
    }

    public void removeIngredientFromStock(Ingredient ingredient, int quantityToBeRemoved){
        int currentQuantity = getIngredientStock(ingredient);
        int updatedQuantity = currentQuantity - quantityToBeRemoved;
        ingredients.put(ingredient, updatedQuantity);
    }

    public void addIngredientToStock(Ingredient ingredient, int quantityToBeAdded){
        int currentQuantity = getIngredientStock(ingredient);
        int updatedQuantity = currentQuantity + quantityToBeAdded;
        ingredients.put(ingredient, updatedQuantity);
    }

    @Override
    public String toString() {
        return "Stock: " + ingredients;
    }
}
