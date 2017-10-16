package com.vladan.recipes.di;

import android.app.Application;

import com.vladan.recipes.AddDataActivity;
import com.vladan.recipes.DetailRecipe.DetailRecipeFragment;
import com.vladan.recipes.Collections.CollectionFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Vladan on 10/8/2017.
 */
@Singleton
@Component (modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {

    void inject(CollectionFragment collectionFragment);
    void inject(DetailRecipeFragment detailRecipeFragment);
    void inject(AddDataActivity addDataActivity);

    Application getApplication();
}
