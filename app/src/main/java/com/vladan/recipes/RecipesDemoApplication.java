package com.vladan.recipes;

import android.app.Application;

import com.vladan.recipes.di.ApplicationComponent;
import com.vladan.recipes.di.ApplicationModule;
import com.vladan.recipes.di.DaggerApplicationComponent;
import com.vladan.recipes.di.RoomModule;

/**
 * Created by Vladan on 10/8/2017.
 */

public class RecipesDemoApplication extends Application{

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();
    }
}
