package com.vladan.recipes.di;

import android.app.Application;

import com.vladan.recipes.activities.AddDataActivity;
import com.vladan.recipes.fragments.DetailRecipeFragment;
import com.vladan.recipes.fragments.ListOfRecipesFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vladan on 10/8/2017.
 */
@Singleton
@Component (modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {

    void inject(ListOfRecipesFragment listOfRecipesFragment);
    void inject(DetailRecipeFragment detailRecipeFragment);
    void inject(AddDataActivity addDataActivity);

    Application application();
}
