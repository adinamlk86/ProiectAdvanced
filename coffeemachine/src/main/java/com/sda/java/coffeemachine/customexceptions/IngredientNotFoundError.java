package com.sda.java.coffeemachine.customexceptions;

import com.sda.java.coffeemachine.menu.Ingredient;

public class IngredientNotFoundError extends Error {

    public IngredientNotFoundError(Ingredient ingredient){
        super("Missing ingredient: "+ ingredient);

    }
}
