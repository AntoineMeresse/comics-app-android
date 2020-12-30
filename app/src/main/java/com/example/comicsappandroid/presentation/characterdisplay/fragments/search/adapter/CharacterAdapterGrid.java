package com.example.comicsappandroid.presentation.characterdisplay.fragments.search.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.comicsappandroid.R;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapterGrid extends RecyclerView.Adapter<CharacterAdapterGrid.CharacterViewHolder> {

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private CharacterActionInterface characterActionInterface;

        private CharacterViewItem characterViewItem;
        private TextView characterNameTextView;

        private ImageView characterImageView;

        // Button fav
        private ToggleButton favButton;
        private Context currentContext;

        public CharacterViewHolder(View view, final CharacterActionInterface characterActionInterface, Context context) {
            super(view);
            this.view = view;
            this.characterActionInterface = characterActionInterface;
            this.characterImageView = view.findViewById(R.id.character_picture);
            this.currentContext = context;

            setupListeners();
        }

        void bind(CharacterViewItem characterViewItem) {
            this.characterViewItem = characterViewItem;

            this.setupImage(characterViewItem);
        }

        /**
         * Method to configure ImageView using Glide.
         * @param characterViewItem
         */
        void setupImage(CharacterViewItem characterViewItem) {
            String imgUrl = characterViewItem.getCharacterImage().getIconUrl();
            if(imgUrl != null) {
                Glide.with(view).load(imgUrl).centerCrop().circleCrop().into(characterImageView);
            }
        }

        /**
         * Method to setup all listeners
         */
        private void setupListeners() {
        }

        public Context getCurrentContext() { return this.currentContext; }
    }

    private CharacterActionInterface characterActionInterface;
    private List<CharacterViewItem> characterViewItemList;
    private Context currentContext;

    public CharacterAdapterGrid(CharacterActionInterface characterActionInterface, Context context) {
        this.characterViewItemList = new ArrayList<>();
        this.characterActionInterface = characterActionInterface;
        this.currentContext = context;
    }

    public Context getCurrentContext() {
        return this.currentContext;
    }

    public void bindViewModels(List<CharacterViewItem> characterViewItemList) {
        this.characterViewItemList.clear();
        this.characterViewItemList.addAll(characterViewItemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharacterAdapterGrid.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recyclerview_item_character_grid,parent,false);
        return new CharacterViewHolder(view, characterActionInterface, getCurrentContext());
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapterGrid.CharacterViewHolder holder, int position) {
        holder.bind(this.characterViewItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.characterViewItemList.size();
    }
}
