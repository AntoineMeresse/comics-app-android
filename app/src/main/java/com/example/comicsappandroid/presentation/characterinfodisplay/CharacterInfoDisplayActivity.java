package com.example.comicsappandroid.presentation.characterinfodisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.comicsappandroid.R;

/**
 * Character Info Display Activity
 */
public class CharacterInfoDisplayActivity extends AppCompatActivity {



    private ImageView imageViewInfo;
    private TextView nameInfo;
    private TextView realNameInfo;
    private TextView descriptionInfo;
    private TextView urlInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info_display);
        getSupportActionBar().setHomeButtonEnabled(true);

        initViews();

        Intent intent = getIntent();
        setupName(intent);
        setupRealName(intent);
        setupDescription(intent);
        setupImage(intent);
        setupUrl(intent);
    }

    /**
     * Method to init views
     */
    public void initViews() {
        this.imageViewInfo = findViewById(R.id.character_info_imageView);
        this.nameInfo = findViewById(R.id.character_info_name);
        this.realNameInfo = findViewById(R.id.character_info_real_name);
        this.descriptionInfo = findViewById(R.id.character_info_description);
        this.urlInfo = findViewById(R.id.character_info_page_url);
    }

    /**
     * Method to setup character name
     * @param i Intent
     */
    public void setupName(Intent i) {
        this.nameInfo.setText(i.getStringExtra("character_name"));
    }

    /**
     * Method to setup character real name
     * @param i Intent
     */
    public void setupRealName(Intent i) {
        this.realNameInfo.setText(i.getStringExtra("character_real_name"));
    }

    /**
     * Method to setup character description
     * @param i Intent
     */
    public void setupDescription(Intent i) {
        this.descriptionInfo.setText(i.getStringExtra("character_description"));
    }

    /**
     * Method to setup character image
     * @param i Intent
     */
    public void setupImage(Intent i) {
        Glide.with(this)
                .load(i.getStringExtra("character_image"))
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this.imageViewInfo);
    }

    /**
     * Method to setup character url
     * @param i Intent
     */
    public void setupUrl(Intent i) {
        this.urlInfo.setText(i.getStringExtra("character_page_url"));
    }
}