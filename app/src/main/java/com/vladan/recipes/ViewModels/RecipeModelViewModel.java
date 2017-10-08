package com.vladan.recipes.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.vladan.recipes.db.RecipeRepository;
import com.vladan.recipes.db.model.RecipeModel;

import java.util.List;


public class RecipeModelViewModel extends ViewModel {

    private RecipeRepository repository;

    AsyncTask mTask;

    //const
    public RecipeModelViewModel(RecipeRepository repository) {
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
        mTask = new AddListOfRecipes();
        mTask.execute(list);
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

        mTask.cancel(true);
        mTask = null;
        repository = null;
    }
}
