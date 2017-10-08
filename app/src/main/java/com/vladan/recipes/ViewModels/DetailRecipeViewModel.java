package com.vladan.recipes.ViewModels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.vladan.recipes.db.RecipeRepository;
import com.vladan.recipes.db.model.RecipeModel;


public class DetailRecipeViewModel extends ViewModel {

    private RecipeRepository repository;

    private MutableLiveData<RecipeModel> mRecipeModel;

    private AsyncTask mTask;
    private boolean running = true;



    public DetailRecipeViewModel(RecipeRepository repository) {
        this.repository = repository;
    }


    //getting recipe
    public MutableLiveData<RecipeModel> getRecipeModel(int id) {
        mRecipeModel = new MutableLiveData<>();
        mTask = new getRecipeModelAsyncTask().execute(id);
        return mRecipeModel;
    }


    // add or remove from favourite section
    public void setFavourite(RecipeModel recipeModel){
        new UpdateAsyncTask().execute(recipeModel);
    }



    //async - updating favourite status
    private class UpdateAsyncTask extends AsyncTask<RecipeModel, Void, Void>{

        @Override
        protected Void doInBackground(RecipeModel... params) {

            repository.updateRecipe(params[0]);
            return null;
        }
    }


    //async for getting single recipe
    private  class  getRecipeModelAsyncTask extends AsyncTask<Integer, Void, RecipeModel>{

        @Override
        protected RecipeModel doInBackground(Integer... params) {

           while (running) {
               return repository.getRecipeModel(params[0]);
           }
           return  null;
        }

        @Override
        protected void onPostExecute(RecipeModel recipeModel) {
            super.onPostExecute(recipeModel);
            mRecipeModel.setValue(recipeModel);
        }

        @Override
        protected void onCancelled() {
            running = false;
        }
    }


    @Override
    protected void onCleared() {
        super.onCleared();

        mTask.cancel(true);
        mTask = null;
    }
}
