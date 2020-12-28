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
import com.example.comicsappandroid.presentation.characterdisplay.fragments.favorite.FavoriteFragment;
import com.example.comicsappandroid.presentation.characterdisplay.fragments.search.SearchFragment;
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
        bottomNavigationView.setSelectedItemId(R.id.item_menu_characters);
    }

    private void setupNavigationElements() {

        fragmentSparseArray = new SparseArray<>(2);

        bottomNavigationView = findViewById(R.id.bottom_navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        int index = getIndex(item.getItemId());

                        if (index >= 0) {

                            Fragment f = fragmentSparseArray.get(index);
                            Log.e("======", "onNavigationItemSelected: " + item.getItemId());

                            if (f == null) {
                                switch (index) {
                                    case 0:
                                        Log.d("Init fragment", "Search Fragment Created");
                                        f = SearchFragment.newInstance();
                                        break;
                                    case 1:
                                        Log.d("Init fragment", "Favorite Fragment Created");
                                        f = FavoriteFragment.newInstance(); // to replace
                                        break;
                                }
                                fragmentSparseArray.put(index, f);
                            }

                            currentFragment = f;
                            replaceFragment(currentFragment);
                        }
                        return true;
                    }
                }
        );
    }

    /**
     * This method was created because getOrder was always returning 0
     * @param fragmentID Integer
     * @return int
     */
    private int getIndex(Integer fragmentID){
        if(fragmentID == R.id.item_menu_characters) return 0;
        else if (fragmentID == R.id.item_menu_favorite) return 1;
        return -1;
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