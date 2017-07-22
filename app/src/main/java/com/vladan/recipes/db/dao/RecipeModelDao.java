package com.vladan.recipes.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.vladan.recipes.db.model.RecipeModel;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


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

    //for trivial adding data
    @Insert (onConflict = REPLACE)
    void addList(List<RecipeModel> list);

    @Insert (onConflict = REPLACE)
    void addRecipe(RecipeModel recipeModel);
}
