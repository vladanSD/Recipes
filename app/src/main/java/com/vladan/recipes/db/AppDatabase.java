package com.vladan.recipes.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.vladan.recipes.db.dao.RecipeModelDao;
import com.vladan.recipes.db.model.RecipeModel;

/**
 * Created by Vladan on 7/21/2017.
 */
@Database(entities = {RecipeModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;


    //singleton
    public static AppDatabase getInstance(Context context){
        if( instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "recipes_db")
                    .build();
        } return instance;
    }

    public abstract RecipeModelDao getRecipeModelDao();
}
