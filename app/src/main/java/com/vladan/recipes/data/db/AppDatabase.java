package com.vladan.recipes.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {RecipeModel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RecipeModelDao getRecipeModelDao();
}
