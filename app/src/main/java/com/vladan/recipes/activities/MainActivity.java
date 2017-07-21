package com.vladan.recipes.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.vladan.recipes.R;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    NavigationView navigationView;
    DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNavigationDrawer();
    }



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
                        break;
                    case R.id.favourite_recipes:
                        mDrawerLayout.closeDrawers();
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

                }
                return true;
            }
        });

       // mDrawerLayout.setStatusBarBackgroundColor(getResources().getColor(R.color.global_color_control_highlight));

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
}


