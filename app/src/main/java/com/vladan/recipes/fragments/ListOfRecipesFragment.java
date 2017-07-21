package com.vladan.recipes.fragments;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladan.recipes.R;
import com.vladan.recipes.adapters.RecipeAdapter;
import com.vladan.recipes.db.model.RecipeModel;

import java.util.ArrayList;



public class ListOfRecipesFragment extends LifecycleFragment implements RecipeAdapter.OnClickedAndSwipedInterface {

    //version of fragment
    private int fragment = -1;

    private static final String STATE = "key";

    private View mRootView;
    private RecipeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;


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
        mLayoutManager = new LinearLayoutManager(getActivity());
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

    }

    @Override
    public void onSwiped(int index) {

    }


}
