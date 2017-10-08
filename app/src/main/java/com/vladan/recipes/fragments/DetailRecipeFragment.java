package com.vladan.recipes.fragments;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vladan.recipes.R;
import com.vladan.recipes.ViewModels.DetailRecipeViewModel;
import com.vladan.recipes.activities.DetailRecipeActivity;
import com.vladan.recipes.db.model.RecipeModel;

import javax.inject.Inject;


public class DetailRecipeFragment extends LifecycleFragment {

    private static final String RECIPE_ID = "23";

    private View mRootView;
    private ImageView mScrollingImage;
    private FloatingActionButton mFab;
    private DetailRecipeViewModel viewModel;
    private RecipeModel mRecipeModel;
    private TextView mTextView;
    private int mId;
    private Toolbar mToolbar;

    @Inject
    ViewModelProvider.Factory viewModelFactory;


    public static DetailRecipeFragment newInstance(int i) {
        Bundle args = new Bundle();
        args.putInt(RECIPE_ID,i);

        DetailRecipeFragment fragment = new DetailRecipeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mId = getArguments().getInt(RECIPE_ID, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_detail_recipe, container, false);

        //init views
        mScrollingImage = (ImageView) mRootView.findViewById(R.id.image_scrolling);
        mFab = (FloatingActionButton) mRootView.findViewById(R.id.fab_scrolling);
        mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar_scroll);
        mTextView = (TextView) mRootView.findViewById(R.id.tv_detail_content) ;
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //set action bar and home button enabled
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        if(((AppCompatActivity)getActivity()).getSupportActionBar()!=null) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        observeRecipeModel();

    }

    @Override
    public void onResume() {
        super.onResume();




    }

    public void init(String image, String title,final int favourites, String tekst){


        //img
        Picasso.with(getActivity()).load(image).into(mScrollingImage);


        // fab starting look
        if(favourites == 1) mFab.setImageResource(R.drawable.ic_menu_favorite_checked);

//        mTextView.setText(tekst);

    }

    private void observeRecipeModel(){
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailRecipeViewModel.class);


        viewModel.getRecipeModel(mId).observe(DetailRecipeFragment.this, new Observer<RecipeModel>() {
            @Override
            public void onChanged(@Nullable final RecipeModel recipeModel) {
                if(recipeModel != null){
                    init(recipeModel.getRecipeImg(), recipeModel.getRecipeName(), recipeModel.getFavouriteRecipes(), recipeModel.getRecipeName());
                mFab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(recipeModel.getFavouriteRecipes()==0) {
                            recipeModel.setFavouriteRecipes(1);
                            viewModel.setFavourite(recipeModel);
                            mFab.setImageResource(R.drawable.ic_menu_favorite_checked);
                        } else{
                            recipeModel.setFavouriteRecipes(0);
                            viewModel.setFavourite(recipeModel);
                            mFab.setImageResource(R.drawable.favorites);
                        }
                    }
                }); }

            }
        });
    }

}
