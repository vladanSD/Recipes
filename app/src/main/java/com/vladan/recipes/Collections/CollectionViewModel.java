package com.vladan.recipes.Collections;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.vladan.recipes.data.RecipeRepository;
import com.vladan.recipes.data.db.RecipeModel;

import java.util.List;


public class CollectionViewModel extends ViewModel {

    private RecipeRepository repository;

    private AsyncTask mTask;

    //const
    public CollectionViewModel(RecipeRepository repository) {
            this.repository = repository;
    }

    public LiveData<List<RecipeModel>> getNewList(){
        return repository.getNewRecipes();
    }

    public LiveData<List<RecipeModel>> getFavList(){
        return  repository.getFavouriteRecipes();
    }

    public LiveData<List<RecipeModel>> getCategoryList(int category){
        return  repository.getRecipeByCategory(category);
    }


    //adding inital data into db
    public void addData(List<RecipeModel> list){
        mTask = new AddListOfRecipes().execute(list);
    }


    private class AddListOfRecipes extends AsyncTask<List<RecipeModel>, Void, Void>{

        @Override
        protected Void doInBackground(List<RecipeModel>... params) {
            repository.addList(params[0]);
            return null;
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();

        mTask = null;
        repository = null;
    }
}
