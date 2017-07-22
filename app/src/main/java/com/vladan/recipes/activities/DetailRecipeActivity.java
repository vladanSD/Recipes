package com.vladan.recipes.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vladan.recipes.R;
import com.vladan.recipes.db.model.RecipeModel;

public class DetailRecipeActivity extends AppCompatActivity {
    private ImageView mScrollingImage;
    private RecipeModel recipeModel;
    private TextView textView;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);


        init();


    }

    private void init(){
        Bundle bundle = getIntent().getExtras();
        recipeModel = (RecipeModel) bundle.getSerializable("recipe");

        setTitle(recipeModel.getRecipeName());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_scroll);
        setSupportActionBar(toolbar);



        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mScrollingImage = (ImageView) findViewById(R.id.image_scrolling);
        textView = (TextView) findViewById(R.id.tv_scrolling);
        Picasso.with(getApplicationContext()).load(recipeModel.getRecipeImg()).into(mScrollingImage);
//        textView.setText(recipeModel.getRecipeName());
        mFab = (FloatingActionButton) findViewById(R.id.fab_scrolling);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
