package com.vladan.recipes.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.vladan.recipes.db.dao.RecipeModelDao;
import com.vladan.recipes.db.model.RecipeModel;



@Database(entities = {RecipeModel.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract RecipeModelDao getRecipeModelDao();
}
