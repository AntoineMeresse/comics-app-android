package com.example.comicsappandroid.presentation.characterdisplay.search.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
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

        // Info Button
        private ImageButton imageButtonInfo;

        /**
         * Constructor
         * @param view View
         * @param characterActionInterface CharacterActionInterface
         * @param context Context
         */
        public CharacterViewHolder(View view, final CharacterActionInterface characterActionInterface, Context context) {
            super(view);
            this.view = view;
            this.characterActionInterface = characterActionInterface;
            this.characterNameTextView = view.findViewById(R.id.character_name);
            this.characterImageView = view.findViewById(R.id.character_picture);
            this.favButton = view.findViewById(R.id.imageButtonFav);
            this.imageButtonInfo = view.findViewById(R.id.imageButtonInfo);
            this.currentContext = context;

            setupListeners();

            this.favButton.setChecked(false);
            isFavIcon();
        }

        /**
         * Method to bind datas
         * @param characterViewItem
         */
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
            infoSetupListeners();
        }

        /**
         * Method to get the current context
         * @return
         */
        public Context getCurrentContext() { return this.currentContext; }

        /**
         * Method to set the fav icon to outline heart
         */
        private void isFavIcon() {
            favButton.setBackgroundDrawable(ContextCompat.getDrawable(getCurrentContext() ,R.drawable.ic_outline_heart));
        }

        /**
         * Method to set the fav icon to full heart
         */
        private void isNotFavIcon() {
            favButton.setBackgroundDrawable(ContextCompat.getDrawable(getCurrentContext() ,R.drawable.ic_full_heart));
        }

        /**
         * Method to setup listener for the fav button
         */
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

        /**
         * Method to setup Listeners for the info button
         */
        private void infoSetupListeners(){
            imageButtonInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Info Button", "onClick: ");
                    characterActionInterface.startInfoActivity(characterViewItem);
                }
            });
        }
    }

    private CharacterActionInterface characterActionInterface;
    private List<CharacterViewItem> characterViewItemList;
    private Context currentContext;

    /**
     * Constructor
     * @param characterActionInterface CharacterActionInterface
     * @param context Context
     */
    public CharacterAdapterLinear(CharacterActionInterface characterActionInterface, Context context) {
        this.characterViewItemList = new ArrayList<>();
        this.characterActionInterface = characterActionInterface;
        this.currentContext = context;
    }

    /**
     * Method to get the current context
     * @return Context
     */
    public Context getCurrentContext() {
        return this.currentContext;
    }

    /**
     * Method to bind the View Models
     * @param characterViewItemList List<CharacterViewItem>
     */
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
