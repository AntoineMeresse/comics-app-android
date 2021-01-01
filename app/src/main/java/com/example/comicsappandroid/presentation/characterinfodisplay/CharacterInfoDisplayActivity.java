package com.example.comicsappandroid.presentation.characterinfodisplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.comicsappandroid.R;

public class CharacterInfoDisplayActivity extends AppCompatActivity {



    private ImageView imageViewInfo;
    private TextView nameInfo;
    private TextView realNameInfo;
    private TextView descriptionInfo;

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
    }

    public void initViews() {
        this.imageViewInfo = findViewById(R.id.character_info_imageView);
        this.nameInfo = findViewById(R.id.character_info_name);
        this.realNameInfo = findViewById(R.id.character_info_real_name);
        this.descriptionInfo = findViewById(R.id.character_info_description);
    }

    public void setupName(Intent i) {
        this.nameInfo.setText(i.getStringExtra("character_name"));
    }

    public void setupRealName(Intent i) {
        this.realNameInfo.setText(i.getStringExtra("character_real_name"));
    }

    public void setupDescription(Intent i) {
        this.descriptionInfo.setText(i.getStringExtra("character_description"));
    }

    public void setupImage(Intent i) {
        Glide.with(this)
                .load(i.getStringExtra("character_image"))
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(this.imageViewInfo);
    }
}