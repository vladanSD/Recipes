package com.vladan.recipes.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.vladan.recipes.db.model.RecipeModel;

import java.util.List;

/**
 * Created by Vladan on 7/21/2017.
 */
@Dao
public interface RecipeModelDao {


    //return new recipes
    @Query("select * from RecipeModel where recipeNew =" +1)
    LiveData<List<RecipeModel>> getNewRecipes();

    //return fav recipes
    @Query("select * from RecipeModel where favouriteRecipes=" +1)
    LiveData<List<RecipeModel>> getFavouriteRecipes();

    //return recipes by category
    @Query("select * from RecipeModel where recipeCategory =:category")
    LiveData<List<RecipeModel>> getRecipeByCategory(String category);

}
