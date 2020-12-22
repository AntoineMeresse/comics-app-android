package com.example.comicsappandroid.presentation.characterdisplay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;

import com.example.comicsappandroid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CharacterDisplayActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private SparseArray<Fragment> fragmentSparseArray;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_display);
        setupNavigationElements();
    }

    private void setupNavigationElements() {

        fragmentSparseArray = new SparseArray<>(2);

        bottomNavigationView = findViewById(R.id.bottom_navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        Fragment f = fragmentSparseArray.get(item.getOrder());

                        if (f == null) {
                            switch (item.getOrder()) {
                                case 0:
                                    // TODO
                                    //  f = SelectedFragment.newInstance(); example
                                    break;
                                case 1:
                                    // TODO
                                    break;
                            }
                            fragmentSparseArray.put(item.getOrder(), f);
                        }

                        currentFragment = f;
                        replaceFragment(currentFragment);

                        return true;
                    }
                }
        );
    }

    private void replaceFragment(Fragment newFragment) {
        if (newFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, newFragment)
                    .addToBackStack("null")
                    .commit();
        }
    }
}