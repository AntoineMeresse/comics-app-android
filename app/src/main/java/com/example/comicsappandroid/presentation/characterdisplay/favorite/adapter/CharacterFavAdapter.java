package com.example.comicsappandroid.presentation.characterdisplay.favorite.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.comicsappandroid.R;

public class CharacterFavAdapter extends RecyclerView.Adapter<CharacterFavAdapter.CharacterFavViewHolder> {

    public static class CharacterFavViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private CharacterFavActionInterface characterFavActionInterface;
        private CharacterFavViewItem characterFavViewItem;

        private TextView characterNameTextView;
        private ImageView characterImageView;
        private ToggleButton favButton;
        private Context currentContext;

        public CharacterFavViewHolder(View view, CharacterFavActionInterface characterFavActionInterface, Context context) {
            super(view);
            this.view = view;
            this.characterFavActionInterface = characterFavActionInterface;
            this.currentContext = context;
            //
            this.characterNameTextView = view.findViewById(R.id.character_name);
            this.characterImageView = view.findViewById(R.id.character_picture);
            this.favButton = view.findViewById(R.id.imageButtonFav);

            setupFavButton();
            setupListeners();
        }

        void bind(CharacterFavViewItem characterFavViewItem) {
            this.characterFavViewItem = characterFavViewItem;
            //
            this.characterNameTextView.setText(characterFavViewItem.getCharacterName());
            this.setupImage();
        }

        private void setupFavButton(){
            this.favButton.setChecked(true);
            favButton.setBackgroundDrawable(ContextCompat.getDrawable(this.currentContext,R.drawable.ic_full_heart));
        }

        private void setupListeners() {
            favSetupListeners();
        }

        private void favSetupListeners() {
            favButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(!isChecked) {
                        Log.d("FAVBUTTON", "onCheckedChanged:"+isChecked);
                    }
                }
            });
        }

        /**
         * Method to configure ImageView using Glide.
         * @param characterFavViewItem
         */
        void setupImage(CharacterFavViewItem characterFavViewItem) {
            String imgUrl = characterFavViewItem.getCharacterImageUrl();
            if(imgUrl != null) {
                Glide.with(view).load(imgUrl).centerCrop().into(characterImageView);
            }
        }
    }

    @NonNull
    @Override
    public CharacterFavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterFavViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
