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
    private LiveData<List<RecipeModel>> newList;
    private LiveData<List<RecipeModel >> favList;
    private LiveData<List<RecipeModel >> firstCategoryList;
    private LiveData<List<RecipeModel >> secondCategoryList;
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
            newList = appDatabase.getRecipeModelDao().getNewRecipes();
            return newList;
        }
        return newList;
    }

    //getting list of fav items
    public LiveData<List<RecipeModel>> getFavList() {
        if(favList == null) {
            favList = appDatabase.getRecipeModelDao().getFavouriteRecipes();
            return  favList;
        }
        return favList;
    }

    //getting recipes from first category
    public LiveData<List<RecipeModel>> getFirstCategoryList() {
        if(firstCategoryList == null) {
            firstCategoryList = appDatabase.getRecipeModelDao().getRecipeByCategory(1);
            return firstCategoryList;
        }
        return firstCategoryList;
    }


    //getting recipes from second category
    public LiveData<List<RecipeModel>> getSecondCategoryList() {
        if(secondCategoryList == null) {
            secondCategoryList = appDatabase.getRecipeModelDao().getRecipeByCategory(2);
            return  secondCategoryList;
        }
        return secondCategoryList;
    }

    //getting recipes from third category
    public LiveData<List<RecipeModel>> getThirdCategoryList() {
        if( thirdCategoryList == null) {
            thirdCategoryList = appDatabase.getRecipeModelDao().getRecipeByCategory(3);
            return  thirdCategoryList;
        }
        return thirdCategoryList;
    }

    //getting recipes from forth category
    public LiveData<List<RecipeModel>> getForthCategoryList() {
        if(forthCategoryList == null) {
            forthCategoryList = appDatabase.getRecipeModelDao().getRecipeByCategory(4);
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
