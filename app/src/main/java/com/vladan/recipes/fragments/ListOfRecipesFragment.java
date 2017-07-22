package com.vladan.recipes.fragments;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladan.recipes.R;
import com.vladan.recipes.ViewModels.RecipeModelViewModel;
import com.vladan.recipes.activities.DetailRecipeActivity;
import com.vladan.recipes.adapters.RecipeAdapter;
import com.vladan.recipes.db.model.RecipeModel;
import com.vladan.recipes.utils.ItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;



public class ListOfRecipesFragment extends LifecycleFragment implements RecipeAdapter.OnClickedAndSwipedInterface {

    //version of fragment
    private int fragment = -1;
    private static final int NEW_RECIPES = 0;
    private static final int FAVOURITES = 1;

    private static final String STATE = "key";

    private View mRootView;
    private RecipeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecipeModelViewModel viewModel;


    public static ListOfRecipesFragment newInstance(int i) {

        Bundle args = new Bundle();
        args.putInt("int_args", i);
        ListOfRecipesFragment fragment = new ListOfRecipesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting version of fragment
        fragment = getArguments().getInt("int_args", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_list_of_recipes, container, false);
        setRecyclerView();
        return mRootView;
    }


    public void setRecyclerView(){
        mLayoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler);
        mAdapter = new RecipeAdapter(new ArrayList<RecipeModel>(), getContext(), this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //restoring state
        if(savedInstanceState!= null){
            Parcelable state = savedInstanceState.getParcelable(STATE);
            mLayoutManager.onRestoreInstanceState(state);
        }
        //getting data
        initData();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //putting parcelable to save state
        outState.putParcelable(
                STATE, mLayoutManager.onSaveInstanceState());
    }

    @Override
    public void onItemClicked(int index) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("recipe", mAdapter.getListOfRecipes().get(index));
        Intent i = new Intent(getActivity(), DetailRecipeActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public void onSwiped(int index) {
        RecipeModel recipe = mAdapter.getListOfRecipes().get(index);
        recipe.setFavouriteRecipes(0);
        viewModel.removeFromFavourites(recipe);
    }



    private void initData(){

        viewModel = ViewModelProviders.of(this).get(RecipeModelViewModel.class);

        switch(fragment){
            case NEW_RECIPES:
                observeNew();
                getActivity().setTitle("New recipes!");
                break;
            case FAVOURITES:
                observeFav();
//                swipeSetupAnimation();
                getActivity().setTitle("Favourites");
                break;

        }
    }

    private void observeNew(){
        viewModel.getNewList().observe(ListOfRecipesFragment.this, new Observer<List<RecipeModel>>() {
            @Override
            public void onChanged(@Nullable List<RecipeModel> recipeModels) {
                    mAdapter.setListOfRecipes(recipeModels);
            }
        });
    }

    private void observeFav(){
        viewModel.getFavList().observe(ListOfRecipesFragment.this, new Observer<List<RecipeModel>>() {
            @Override
            public void onChanged(@Nullable List<RecipeModel> recipeModels) {
                mAdapter.setListOfRecipes(recipeModels);
            }
        });
    }

    public void swipeSetupAnimation(){
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(mAdapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

}
