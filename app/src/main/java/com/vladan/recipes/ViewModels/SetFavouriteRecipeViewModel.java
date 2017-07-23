package com.vladan.recipes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import com.vladan.recipes.db.AppDatabase;
import com.vladan.recipes.db.model.RecipeModel;


public class SetFavouriteRecipeViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;


    public SetFavouriteRecipeViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
    }

    public void setFavourite(RecipeModel recipeModel){
        new UpdateAsyncTask(appDatabase).execute(recipeModel);
    }


    private class UpdateAsyncTask extends AsyncTask<RecipeModel, Void, Void>{
        AppDatabase db;

        public UpdateAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(RecipeModel... params) {
            db.getRecipeModelDao().updateRecipe(params[0]);
            return null;
        }
    }
}
