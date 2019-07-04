package com.example.sdjki.amigo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class category extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
        private Toolbar mToolbar;
        private DrawerLayout mDrawerLayout;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mainscroll);
            setupToolbarMenu();
            setupNavigationDrawerMenu();

        }
        private void setupToolbarMenu()
        {
            mToolbar=(Toolbar)findViewById(R.id.toolbar);
            mToolbar.setTitle("Amigo");
        }

        private void setupNavigationDrawerMenu(){
            NavigationView navigationView=(NavigationView)findViewById(R.id.navigationView);
            mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
            navigationView.setNavigationItemSelectedListener(this);
            ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,
                    mDrawerLayout,
                    mToolbar,
                    R.string.drawer_open,
                    R.string.drawer_close
            );
            mDrawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState();
        }
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){
            String itemName=(String)menuItem.getTitle();
            closeDrawer();
            Intent intent = new Intent("com.example.sdjki.amigo.category");
            switch (menuItem.getItemId()){
                case R.id.item_food:
                    startActivity(intent);
                    break;
                case R.id.item_fashion:
                    startActivity(intent);
                    break;
                case R.id.item_beauty:
                    startActivity(intent);
                    break;
                case R.id.item_entertainment:
                    startActivity(intent);
                    break;
                case R.id.item_home:
                    startActivity(intent);
                    break;
                case R.id.item_tech:
                    startActivity(intent);
                    break;
                case R.id.item_gifts:
                    startActivity(intent);
                    break;
                case R.id.item_jewellery:
                    startActivity(intent);
                    break;
                case R.id.item_sports:
                    startActivity(intent);
                    break;
                case R.id.item_books:
                    startActivity(intent);
                    break;
            }
            return true;
        }
        private void closeDrawer(){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    public void onBackPressed(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
            closeDrawer();
        else
            super.onBackPressed();
    }
    }

