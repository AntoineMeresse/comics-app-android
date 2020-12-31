package com.example.comicsappandroid.presentation.characterdisplay.favorite.adapter;

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
            this.setupImage(characterFavViewItem);
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

    private CharacterFavActionInterface characterFavActionInterface;
    private List<CharacterFavViewItem> characterFavViewItemList;
    private Context currentContext;

    public CharacterFavAdapter(CharacterFavActionInterface characterFavActionInterface, Context context) {
        this.characterFavViewItemList = new ArrayList<>();
        this.characterFavActionInterface = characterFavActionInterface;
        this.currentContext = context;
    }

    public Context getCurrentContext() {
        return this.currentContext;
    }

    public void bindViewModels(List<CharacterFavViewItem> characterFavViewItemList){
        this.characterFavViewItemList.clear();
        this.characterFavViewItemList.addAll(characterFavViewItemList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharacterFavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recyclerview_item_favorite_characters,parent,false);
        return new CharacterFavViewHolder(view, characterFavActionInterface, getCurrentContext());
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterFavViewHolder holder, int position) {
        holder.bind(this.characterFavViewItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.characterFavViewItemList.size();
    }
}
