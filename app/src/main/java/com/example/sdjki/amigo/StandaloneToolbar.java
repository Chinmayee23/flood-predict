package com.example.sdjki.amigo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


    public class StandaloneToolbar extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_toolbar);
            Toolbar mToolbar=(Toolbar)findViewById(R.id.toolbar);
            mToolbar.setTitle("Amigo");
            setSupportActionBar(mToolbar);

        }
    }

