package com.vladan.recipes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.vladan.recipes.db.AppDatabase;
import com.vladan.recipes.db.model.RecipeModel;

import java.util.List;



public class ThirdCategoryViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;
    private LiveData<List<RecipeModel>> mThirdCategoryList;

    public ThirdCategoryViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
        mThirdCategoryList = appDatabase.getRecipeModelDao().getRecipeByCategory(3);
    }

    public LiveData<List<RecipeModel>> getmThirdCategoryList() {
        return mThirdCategoryList;
    }
}
