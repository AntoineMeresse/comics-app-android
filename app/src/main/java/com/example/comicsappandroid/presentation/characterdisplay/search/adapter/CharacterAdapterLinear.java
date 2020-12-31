package com.example.comicsappandroid.presentation.characterdisplay.search.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
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

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapterLinear extends RecyclerView.Adapter<CharacterAdapterLinear.CharacterViewHolder> {

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
            this.characterNameTextView = view.findViewById(R.id.character_name);
            this.characterImageView = view.findViewById(R.id.character_picture);
            this.favButton = view.findViewById(R.id.imageButtonFav);
            this.currentContext = context;

            setupListeners();

            this.favButton.setChecked(false);
            isFavIcon();
        }

        void bind(CharacterViewItem characterViewItem) {
            this.characterViewItem = characterViewItem;
            this.characterNameTextView.setText(characterViewItem.getCharacterName());
            this.favButton.setChecked(characterViewItem.isFavorite());
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

        public Context getCurrentContext() { return this.currentContext; }

        private void isFavIcon() {
            favButton.setBackgroundDrawable(ContextCompat.getDrawable(getCurrentContext() ,R.drawable.ic_outline_heart));
        }

        private void isNotFavIcon() {
            favButton.setBackgroundDrawable(ContextCompat.getDrawable(getCurrentContext() ,R.drawable.ic_full_heart));
        }

        private void favSetupListeners() {
            favButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.d("FAVBUTTON", "onCheckedChanged:"+characterViewItem.getCharacterID()+" | "+isChecked);
                    if(isChecked) {
                        isNotFavIcon();
                    }
                    else {
                        isFavIcon();
                    }
                    characterActionInterface.onHeartClick(characterViewItem.getCharacterID(), isChecked);
                }
            });
        }
    }

    private CharacterActionInterface characterActionInterface;
    private List<CharacterViewItem> characterViewItemList;
    private Context currentContext;

    public CharacterAdapterLinear(CharacterActionInterface characterActionInterface, Context context) {
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
    public CharacterAdapterLinear.CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recyclerview_item_character,parent,false);
        return new CharacterViewHolder(view, characterActionInterface, getCurrentContext());
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterAdapterLinear.CharacterViewHolder holder, int position) {
        holder.bind(this.characterViewItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.characterViewItemList.size();
    }
}
