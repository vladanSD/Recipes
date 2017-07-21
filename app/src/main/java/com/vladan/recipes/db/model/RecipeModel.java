package com.vladan.recipes.db.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Vladan on 7/21/2017.
 */

@Entity
public class RecipeModel {

    @PrimaryKey (autoGenerate = true)
    private int recipeId;

    private int recipeNew;

    private int favouriteRecipes;

    private String recipeName;

    private String recipeImg;

    private String recipeCategory;

    private String recipeAuthor;

    public RecipeModel(int recipeId, int recipeNew, int favouriteRecipes, String recipeName, String recipeImg, String recipeCategory, String recipeAuthor) {
        this.recipeId = recipeId;
        this.recipeNew = recipeNew;
        this.favouriteRecipes = favouriteRecipes;
        this.recipeName = recipeName;
        this.recipeImg = recipeImg;
        this.recipeCategory = recipeCategory;
        this.recipeAuthor = recipeAuthor;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getRecipeNew() {
        return recipeNew;
    }

    public void setRecipeNew(int recipeNew) {
        this.recipeNew = recipeNew;
    }

    public int getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(int favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeImg() {
        return recipeImg;
    }

    public void setRecipeImg(String recipeImg) {
        this.recipeImg = recipeImg;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public String getRecipeAuthor() {
        return recipeAuthor;
    }

    public void setRecipeAuthor(String recipeAuthor) {
        this.recipeAuthor = recipeAuthor;
    }
}
