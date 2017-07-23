package com.vladan.recipes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.vladan.recipes.db.AppDatabase;
import com.vladan.recipes.db.model.RecipeModel;

import java.util.List;

/**
 * Created by Vladan on 7/23/2017.
 */

public class FavouritesViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    private LiveData<List<RecipeModel >> mFavList;

    public FavouritesViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
        mFavList = appDatabase.getRecipeModelDao().getFavouriteRecipes();
    }

    public LiveData<List<RecipeModel>> getFavList() {
        return mFavList;
    }
}
