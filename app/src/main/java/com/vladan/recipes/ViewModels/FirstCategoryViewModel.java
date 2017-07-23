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

public class FirstCategoryViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    private LiveData<List<RecipeModel >> mFirstCategoryList;

    public FirstCategoryViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
        mFirstCategoryList = appDatabase.getRecipeModelDao().getRecipeByCategory(1);
    }

    public LiveData<List<RecipeModel>> getmFirstCategoryList() {
        return mFirstCategoryList;
    }
}
