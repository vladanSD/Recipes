package com.vladan.recipes.di;

import android.app.Application;

import com.vladan.recipes.RecipesDemoApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Vladan on 10/8/2017.
 */

@Module
public class ApplicationModule {

    private final RecipesDemoApplication application;

    public ApplicationModule(RecipesDemoApplication application) {
        this.application = application;
    }

    @Provides
    RecipesDemoApplication provideRecipesDemoApplication(){ return application; }

    @Provides
    Application provideApplication(){ return application; }
}
