package com.vladan.recipes.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.vladan.recipes.ViewModels.CustomViewModelFactory;
import com.vladan.recipes.data.db.AppDatabase;
import com.vladan.recipes.data.RecipeRepository;
import com.vladan.recipes.data.db.RecipeModelDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vladan on 10/8/2017.
 */
@Module
public class RoomModule {

    private final AppDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(
                application,
                AppDatabase.class,
                "database_recipes"
        ).build();
    }

    @Provides
    @Singleton
    RecipeRepository provideRecipeRepository(RecipeModelDao recipeModelDao){
        return new RecipeRepository(recipeModelDao);
    }

    @Provides
    @Singleton
    RecipeModelDao provideRecipeModelDao(AppDatabase database){
        return database.getRecipeModelDao();
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(){
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(RecipeRepository repository){
        return new CustomViewModelFactory(repository);
    }
}
