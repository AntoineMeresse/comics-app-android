package com.example.comicsappandroid.presentation.characterdisplay.favorite.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CharacterFavAdapter extends RecyclerView.Adapter<CharacterFavAdapter.CharacterFavViewHolder> {

    public static class CharacterFavViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private CharacterFavActionInterface characterFavActionInterface;
        private CharacterFavViewItem characterFavViewItem;

        private TextView characterNameTextView;
        private ImageView characterImageView;

        public CharacterFavViewHolder(View view, CharacterFavActionInterface characterFavActionInterface) {
            super(view);
            this.view = view;
            this.characterFavActionInterface = characterFavActionInterface;
            //
        }

        private void setupListeners() {

        }

        void bind(CharacterFavViewItem characterFavViewItem) {
            this.characterFavViewItem = characterFavViewItem;
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
