package com.example.comicsappandroid.presentation.characterdisplay.fragments.search.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comicsappandroid.data.api.models.Character;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {

        private View view;

        public CharacterViewHolder(View view, final CharacterActionInterface characterActionInterface) {
            super(view);
            this.view = view;
        }
    }

    @NonNull
    @Override
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CharacterViewHolder holder, int position) {
        // TODO
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
