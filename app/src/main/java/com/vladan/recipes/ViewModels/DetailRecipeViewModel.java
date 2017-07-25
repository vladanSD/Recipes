package com.vladan.recipes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.vladan.recipes.db.AppDatabase;
import com.vladan.recipes.db.model.RecipeModel;


public class DetailRecipeViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private MutableLiveData<RecipeModel> mRecipeModel;



    public DetailRecipeViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());
    }

    public MutableLiveData<RecipeModel> getRecipeModel(int id) {
        mRecipeModel = new MutableLiveData<>();
        loadRecipe(id);
        return mRecipeModel;
    }

    private void loadRecipe(int id) {
        new getRecipeModelAsyncTask(appDatabase).execute(id);
    }

    private void setmRecipeModel(RecipeModel recipeModel) {
        mRecipeModel.setValue(recipeModel);
    }

    public void setFavourite(RecipeModel recipeModel){
        new UpdateAsyncTask(appDatabase).execute(recipeModel);
    }


    private class UpdateAsyncTask extends AsyncTask<RecipeModel, Void, Void>{
        AppDatabase db;

        private UpdateAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(RecipeModel... params) {
            db.getRecipeModelDao().updateRecipe(params[0]);
            return null;
        }
    }

    private  class  getRecipeModelAsyncTask extends AsyncTask<Integer, Void, RecipeModel>{

        AppDatabase db;

        private getRecipeModelAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected RecipeModel doInBackground(Integer... params) {
           return db.getRecipeModelDao().getRecipeModel(params[0]);

        }

        @Override
        protected void onPostExecute(RecipeModel recipeModel) {
            super.onPostExecute(recipeModel);
            setmRecipeModel(recipeModel);
        }
    }



}
