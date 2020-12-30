package com.example.comicsappandroid.presentation.characterdisplay.fragments.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.comicsappandroid.R;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private CharacterActionInterface characterActionInterface;

        private CharacterViewItem characterViewItem;
        private TextView characterNameTextView;

        private ImageView characterImageView;

        // Button fav
        private ImageButton imageButtonFav;

        public CharacterViewHolder(View view, final CharacterActionInterface characterActionInterface) {
            super(view);
            this.view = view;
            this.characterActionInterface = characterActionInterface;
            this.characterNameTextView = view.findViewById(R.id.character_name);
            this.characterImageView = view.findViewById(R.id.character_picture);
            this.imageButtonFav = view.findViewById(R.id.imageButtonFav);

            setupListeners();
        }

        void bind(CharacterViewItem characterViewItem) {
            this.characterViewItem = characterViewItem;
            this.characterNameTextView.setText(characterViewItem.getCharacterName());

            this.setupImage(characterViewItem);
        }

        /**
         * Method to configure ImageView using Glide.
         * @param characterViewItem
         */
        void setupImage(CharacterViewItem characterViewItem) {
            String imgUrl = characterViewItem.getCharacterImage().getIconUrl();
            if(imgUrl != null) {
                Glide.with(view).load(imgUrl).centerCrop().into(characterImageView);
            }
        }

        /**
         * Method to setup all listeners
         */
        private void setupListeners() {
            favSetupListeners();
        }

        private void favSetupListeners() {
            imageButtonFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    characterActionInterface.onHeartClick(characterViewItem.getCharacterID(), true);
                }
            });
        }
    }

    private CharacterActionInterface characterActionInterface;
    private List<CharacterViewItem> characterViewItemList;

    public CharacterAdapter(CharacterActionInterface characterActionInterface) {
        this.characterViewItemList = new ArrayList<>();
        this.characterActionInterface = characterActionInterface;
    }

    public CharacterAdapter() {
        this.characterViewItemList = new ArrayList<>();
    }

    public void bindViewModels(List<CharacterViewItem> characterViewItemList) {
        this.characterViewItemList.clear();
        this.characterViewItemList.addAll(characterViewItemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharacterAdapter.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recyclerview_item_character,parent,false);
        return new CharacterViewHolder(view, characterActionInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapter.CharacterViewHolder holder, int position) {
        holder.bind(this.characterViewItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.characterViewItemList.size();
    }
}
