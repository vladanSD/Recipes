package com.vladan.recipes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.vladan.recipes.db.AppDatabase;
import com.vladan.recipes.db.model.RecipeModel;

import java.util.List;



public class SecondCategoryViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;

    private LiveData<List<RecipeModel >> mSecondCategoryList;

    public SecondCategoryViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
        mSecondCategoryList = appDatabase.getRecipeModelDao().getRecipeByCategory(2);
    }

    public LiveData<List<RecipeModel>> getmSecondCategoryList() {
        return mSecondCategoryList;
    }
}
