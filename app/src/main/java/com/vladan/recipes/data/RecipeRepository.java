package com.vladan.recipes.data;

import android.arch.lifecycle.LiveData;

import com.vladan.recipes.data.db.RecipeModelDao;
import com.vladan.recipes.data.db.RecipeModel;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Vladan on 10/8/2017.
 */

public class RecipeRepository {

    private final RecipeModelDao recipeModelDao;

    @Inject
    public RecipeRepository(RecipeModelDao recipeModelDao) {
        this.recipeModelDao = recipeModelDao;
    }

    public RecipeModel getRecipeModel(int id){
        return recipeModelDao.getRecipeModel(id);
    }

    public LiveData<List<RecipeModel>> getNewRecipes(){
        return recipeModelDao.getNewRecipes();
    }

    public LiveData<List<RecipeModel>> getFavouriteRecipes(){
        return recipeModelDao.getFavouriteRecipes();
    }

    public LiveData<List<RecipeModel>> getRecipeByCategory(int category){
        return recipeModelDao.getRecipeByCategory(category);
    }

    public void addList(List<RecipeModel> list){
        recipeModelDao.addList(list);
    }

    public void addRecipe(RecipeModel recipe){
        recipeModelDao.addRecipe(recipe);
    }

    public void updateRecipe(RecipeModel recipe){
        recipeModelDao.updateRecipe(recipe);
    }
}
