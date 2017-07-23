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

public class ForthCategoryViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;
    private LiveData<List<RecipeModel>> mForthCategoryList;

    public ForthCategoryViewModel(Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(this.getApplication());
        mForthCategoryList = appDatabase.getRecipeModelDao().getRecipeByCategory(4);
    }

    public LiveData<List<RecipeModel>> getmForthCategoryList() {
        return mForthCategoryList;
    }
}
