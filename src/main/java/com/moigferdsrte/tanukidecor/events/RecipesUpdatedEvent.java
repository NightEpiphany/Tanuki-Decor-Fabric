package com.moigferdsrte.tanukidecor.events;

import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.ApiStatus;

public record RecipesUpdatedEvent(RecipeManager recipeManager) implements IEvent {
    @ApiStatus.Internal
    public RecipesUpdatedEvent {
    }

    /**
     * {@return the recipe manager}
     */
    public RecipeManager getRecipeManager() {
        return recipeManager;
    }

    @Override
    public void post() {
        CALLBACK.invoker().post(this);
    }
}
