package com.vladan.recipes.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vladan.recipes.R;
import com.vladan.recipes.ViewModels.RecipeModelViewModel;
import com.vladan.recipes.db.model.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public class AddDataActivity extends AppCompatActivity {
    RecipeModelViewModel viewModel;
    List<RecipeModel> mList = new ArrayList<>();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        viewModel = ViewModelProviders.of(this).get(RecipeModelViewModel.class);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
                finish();
            }
        });


    }

    private void addData(){
        RecipeModel rm1 = new RecipeModel(1,0,1, "recipe 1","https://cdn.pixabay.com/photo/2016/03/09/22/50/food-1247612_960_720.jpg",1, "", "author");
        RecipeModel rm2 = new RecipeModel(2,0,0, "recipe 2", "https://cdn.pixabay.com/photo/2014/12/08/15/07/salmon-560987_960_720.jpg",1, "", "author" );
        RecipeModel rm3 = new RecipeModel(3,0,0, "recipe 3", "https://cdn.pixabay.com/photo/2014/11/05/16/00/thai-food-518035_960_720.jpg",1, "", "author");
        RecipeModel rm4 = new RecipeModel(4,1,0, "recipe 4", "https://cdn.pixabay.com/photo/2015/02/15/17/14/potatoes-637370_960_720.jpg",2, "", "author");
        RecipeModel rm5 = new RecipeModel(5,1,0, "recipe 5", "https://cdn.pixabay.com/photo/2014/08/14/14/21/shish-kebab-417994_960_720.jpg", 2, "", "author");
        RecipeModel rm6 = new RecipeModel(6,1,0, "recipe 6", "https://cdn.pixabay.com/photo/2014/10/16/17/23/sushi-491425_960_720.jpg",2, "", "author");


        RecipeModel rm7 = new RecipeModel(7,1,0, "recipe 7", "https://cdn.pixabay.com/photo/2017/03/23/19/57/asparagus-2169305_960_720.jpg",3, "", "author");
        RecipeModel rm8 = new RecipeModel(8,1,1, "recipe 8", "https://image.shutterstock.com/display_pic_with_logo/105328/397498693/stock-photo-grilling-steaks-on-flaming-grill-and-shot-with-selective-focus-397498693.jpg", 3, "", "author");
        RecipeModel rm9 = new RecipeModel(9,1,0, "recipe 9", "https://cdn.pixabay.com/photo/2016/11/29/07/03/blur-1867981_960_720.jpg",3, "", "author");
        RecipeModel rm10 = new RecipeModel(10,1,1, "recipe 10", "https://cdn.pixabay.com/photo/2017/07/16/12/07/beef-2509104_960_720.jpg",4, "", "author");
        RecipeModel rm11= new RecipeModel(11,1,0, "recipe 11", "https://cdn.pixabay.com/photo/2014/11/03/23/33/food-516044_960_720.jpg", 4, "", "author");
        RecipeModel rm12 = new RecipeModel(12,1,0, "recipe 12", "https://cdn.pixabay.com/photo/2017/06/18/18/12/steak-2416655_960_720.jpg",4, "", "author");



        mList.add(rm1);
        mList.add(rm2);
        mList.add(rm3);
        mList.add(rm4);
        mList.add(rm5);
        mList.add(rm6);
        mList.add(rm7);
        mList.add(rm8);
        mList.add(rm9);
        mList.add(rm10);
        mList.add(rm11);
        mList.add(rm12);

        viewModel.addData(mList);

    }
}
