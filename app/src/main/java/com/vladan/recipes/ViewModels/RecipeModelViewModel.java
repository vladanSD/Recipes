package com.vladan.recipes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.vladan.recipes.db.AppDatabase;
import com.vladan.recipes.db.model.RecipeModel;

import java.util.List;


public class RecipeModelViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private LiveData<List<RecipeModel>> mNewList;
    private LiveData<List<RecipeModel>> mFavList;


    //constr
    public RecipeModelViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
        mNewList = appDatabase.getRecipeModelDao().getNewRecipes();
        mFavList = appDatabase.getRecipeModelDao().getFavouriteRecipes();
    }

    //getting list of new items
    public LiveData<List<RecipeModel>> getNewList(){
        return mNewList;
    }

    //getting list of favourite items
    public LiveData<List<RecipeModel>> getFavList(){
        return mFavList;
    }
}
