package com.example.comicsappandroid.presentation.characterdisplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.comicsappandroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CharacterDisplayActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_display);
        setupBottomView();
    }

    private void setupBottomView() {
        bottomNavigationView = findViewById(R.id.bottom_navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        updateMainFragment(item.getItemId());
                        return true;
                    }
                }
        );
    }

    private Boolean updateMainFragment(Integer itemID) {
        switch (itemID) {
            case R.id.item_menu_characters:
                Log.d("FRAGMENT Infos :", "Update Character Fragment");
                break;
            case R.id.item_menu_favorite:
                Log.d("FRAGMENT Infos :", "Update Favorite Fragment");
                break;
        }
        return true;
    }
}