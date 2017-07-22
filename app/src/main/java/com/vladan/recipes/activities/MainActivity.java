package com.vladan.recipes.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.vladan.recipes.R;
import com.vladan.recipes.fragments.ListOfRecipesFragment;

public class MainActivity extends AppCompatActivity {

    private static final String ACTIVE_FRAGMENT = "active";

    Toolbar mToolbar;
    NavigationView navigationView;
    DrawerLayout mDrawerLayout;
    ListOfRecipesFragment activeFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNavigationDrawer();

        //restoring fragment instance
        if(savedInstanceState != null ){
            Log.i("State", "restored");
            activeFragment = (ListOfRecipesFragment) getSupportFragmentManager().getFragment(savedInstanceState, ACTIVE_FRAGMENT);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, activeFragment).commit();
        } else {
            Log.i("State", "no state found, initialaling fragment");
            navigationView.getMenu().getItem(0).setChecked(true);
            activeFragment = ListOfRecipesFragment.newInstance(0);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, activeFragment).commit();
        }
    }







    //setting up drawer
    public void initNavigationDrawer(){

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);


        setSupportActionBar(mToolbar);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id =item.getItemId();

                switch (id){
                    case R.id.new_recipes:
                        mDrawerLayout.closeDrawers();
                        activeFragment = ListOfRecipesFragment.newInstance(0);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, activeFragment).commit();
                        break;
                    case R.id.favourite_recipes:
                        mDrawerLayout.closeDrawers();
                        activeFragment = ListOfRecipesFragment.newInstance(1);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_fragment, activeFragment).commit();
                        break;
                    case R.id.firstCategory:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.secondCategory:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.thirdCategory:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.init_data:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(MainActivity.this, AddDataActivity.class));
                        break;

                }
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState,ACTIVE_FRAGMENT, activeFragment );
    }
}


