package com.vladan.recipes.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import com.vladan.recipes.R;
import com.vladan.recipes.ViewModels.DetailRecipeViewModel;
import com.vladan.recipes.fragments.DetailRecipeFragment;

public class DetailRecipeActivity extends AppCompatActivity {

    private String DETAIL_FRAGMENT = "detail fragment";
    private int mId;
    private String mTitle;
    private DetailRecipeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recipe);


        Bundle bundle = getIntent().getExtras();
        mId =  bundle.getInt("recipe");
        mTitle = bundle.getString("name");
        setTitle(mTitle);
        if(getSupportFragmentManager().findFragmentByTag(DETAIL_FRAGMENT) == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.cl_detail_fragment_content, DetailRecipeFragment.newInstance(mId), DETAIL_FRAGMENT )
                    .commit();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
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
