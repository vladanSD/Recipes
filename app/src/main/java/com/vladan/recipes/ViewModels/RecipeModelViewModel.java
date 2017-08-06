package com.vladan.recipes.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.vladan.recipes.db.AppDatabase;
import com.vladan.recipes.db.model.RecipeModel;

import java.util.List;


public class RecipeModelViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    //holding data
    private LiveData<List<RecipeModel>> newList;
    private LiveData<List<RecipeModel>> favList;
    private LiveData<List<RecipeModel>> firstCategoryList;
    private LiveData<List<RecipeModel>> secondCategoryList;
    private LiveData<List<RecipeModel>> thirdCategoryList;
    private LiveData<List<RecipeModel>> forthCategoryList;


    //const
    public RecipeModelViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getInstance(this.getApplication());

    }


    //getting list of new items
    public LiveData<List<RecipeModel>> getNewList(){
        if(newList==null) {
            newList = new MutableLiveData<>();
            newList = appDatabase.getRecipeModelDao().getNewRecipes();
            Log.i("retrive_data", "List of new recipes");
            return newList;
        }
        return newList;
    }

    //getting list of fav items
    public LiveData<List<RecipeModel>> getFavList() {
        if(favList == null) {
            favList = appDatabase.getRecipeModelDao().getFavouriteRecipes();
            Log.i("retrive_data", "List of favourite recipes");
            return  favList;
        }
        return favList;
    }

    //getting recipes from first category
    public LiveData<List<RecipeModel>> getFirstCategoryList() {
        if(firstCategoryList == null) {
            firstCategoryList = new MutableLiveData<>();
            firstCategoryList = appDatabase.getRecipeModelDao().getRecipeByCategory(1);
            Log.i("retrive_data", "First category recipes");
            return firstCategoryList;
        }
        return firstCategoryList;
    }


    //getting recipes from second category
    public LiveData<List<RecipeModel>> getSecondCategoryList() {
        if(secondCategoryList == null) {
            secondCategoryList = new MutableLiveData<>();
            secondCategoryList = appDatabase.getRecipeModelDao().getRecipeByCategory(2);
            Log.i("retrive_data", "Second category recipes");
            return  secondCategoryList;
        }
        return secondCategoryList;
    }

    //getting recipes from third category
    public LiveData<List<RecipeModel>> getThirdCategoryList() {
        if( thirdCategoryList == null) {
            thirdCategoryList = new MutableLiveData<>();
            thirdCategoryList = appDatabase.getRecipeModelDao().getRecipeByCategory(3);
            Log.i("retrive_data", "Third category recipes");
            return  thirdCategoryList;
        }
        return thirdCategoryList;
    }

    //getting recipes from forth category
    public LiveData<List<RecipeModel>> getForthCategoryList() {
        if(forthCategoryList == null) {
            forthCategoryList = new MutableLiveData<>();
            forthCategoryList = appDatabase.getRecipeModelDao().getRecipeByCategory(4);
            Log.i("retrive_data", "Forth category recipes");
            return  forthCategoryList;
        }
        return forthCategoryList;
    }

    //adding inital data into db
    public void addData(List<RecipeModel> list){
        new AddListOfRecipes(appDatabase).execute(list);
    }

    //removing from favourites
    public void removeFromFavourites(RecipeModel recipe){new AddSingleAsyncTask(appDatabase).execute(recipe);}



    private class AddListOfRecipes extends AsyncTask<List<RecipeModel>, Void, Void>{
        AppDatabase db;

        private AddListOfRecipes(AppDatabase db) {
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

        private AddSingleAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(RecipeModel... params) {
            db.getRecipeModelDao().addRecipe(params[0]);
            return null;
        }
    }
}
