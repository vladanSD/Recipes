package com.vladan.recipes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.vladan.recipes.db.AppDatabase;
import com.vladan.recipes.db.model.RecipeModel;

import java.util.List;


public class RecipeModelViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private LiveData<List<RecipeModel>> mNewList;
    private LiveData<List<RecipeModel>> mFavList;



    //const
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

    //adding inital data into db
    public void addData(List<RecipeModel> list){
        new AddListOfRecipes(appDatabase).execute(list);
    }

    //removing from favourites
    public void removeFromFavourites(RecipeModel recipe){new AddSingleAsyncTask(appDatabase).execute(recipe);}



    private class AddListOfRecipes extends AsyncTask<List<RecipeModel>, Void, Void>{
        AppDatabase db;

        protected AddListOfRecipes(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(List<RecipeModel>... params) {
            db.getRecipeModelDao().addList(params[0]);
            return null;
        }
    }

    private static class AddSingleAsyncTask extends AsyncTask<RecipeModel, Void, Void>{
        AppDatabase db;

        protected AddSingleAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(RecipeModel... params) {
            db.getRecipeModelDao().addRecipe(params[0]);
            return null;
        }
    }
}
