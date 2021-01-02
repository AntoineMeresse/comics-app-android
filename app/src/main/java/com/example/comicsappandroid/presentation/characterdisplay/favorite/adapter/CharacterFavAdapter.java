package com.example.comicsappandroid.presentation.characterdisplay.favorite.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ScrollView;
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

        private TextView characterRealName;
        private TextView characterDescription;
        private ScrollView scrollView;

        /**
         * Constructor
         * @param view View
         * @param characterFavActionInterface CharacterFavActionInterface
         * @param context Context
         */
        public CharacterFavViewHolder(View view, CharacterFavActionInterface characterFavActionInterface, Context context) {
            super(view);
            this.view = view;
            this.characterFavActionInterface = characterFavActionInterface;
            this.currentContext = context;
            //
            this.characterNameTextView = view.findViewById(R.id.character_name);
            this.characterImageView = view.findViewById(R.id.character_picture);
            this.favButton = view.findViewById(R.id.imageButtonFav);

            this.characterRealName = view.findViewById(R.id.character_real_name);
            this.characterDescription = view.findViewById(R.id.character_description);

            setupFavButton();
            setupListeners();
            setupScrollView();
        }

        /**
         * Method to bind datas
         * @param characterFavViewItem
         */
        void bind(CharacterFavViewItem characterFavViewItem) {
            this.characterFavViewItem = characterFavViewItem;
            //
            this.characterNameTextView.setText(characterFavViewItem.getCharacterName());
            this.characterRealName.setText("Real Name : "+characterFavViewItem.getRealName());
            this.characterDescription.setText("Description : "+characterFavViewItem.getDescription());
            this.setupImage(characterFavViewItem);
        }

        /**
         * Method to setup FavButton
         */
        private void setupFavButton(){
            this.favButton.setChecked(true);
            favButton.setBackgroundDrawable(ContextCompat.getDrawable(this.currentContext,R.drawable.ic_full_heart));
        }

        /**
         * Method to setup Listeners
         */
        private void setupListeners() {
            favSetupListeners();
        }

        /**
         * Method to setup Listener for the fav button
         */
        private void favSetupListeners() {
            favButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(!isChecked) {
                        Log.d("FAVBUTTON", "onCheckedChanged:"+isChecked);
                        AlertDialog diaBox = askToDelete(characterFavViewItem.getCharacterID());
                        diaBox.show();
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

        /**
         * Method for the confirmation alert dialog when delete button clicked
         * @param id String
         * @return AlertDialog
         */
        private AlertDialog askToDelete(final String id){
            AlertDialog myQuittingDialogBox = new AlertDialog.Builder(currentContext)
                    // set message, title, and icon
                    .setTitle("Delete Character")
                    .setMessage("Do you want to delete this character from your favorite list ?")
                    .setIcon(R.drawable.ic_delete_icon)

                    .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            characterFavActionInterface.removeFromFavorite(id);
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            setupFavButton();
                            dialog.dismiss();
                        }
                    })
                    .create();

            return myQuittingDialogBox;
        }

        /**
         * Method to setup the scoll view for the description
         */
        private void setupScrollView(){
            this.scrollView = view.findViewById(R.id.character_scrollView);
            scrollView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    v.onTouchEvent(event);
                    return true;
                }
            });
        }
    }

    private CharacterFavActionInterface characterFavActionInterface;
    private List<CharacterFavViewItem> characterFavViewItemList;
    private Context currentContext;

    /**
     * Constructor
     * @param characterFavActionInterface CharacterFavActionInterface
     * @param context Context
     */
    public CharacterFavAdapter(CharacterFavActionInterface characterFavActionInterface, Context context) {
        this.characterFavViewItemList = new ArrayList<>();
        this.characterFavActionInterface = characterFavActionInterface;
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
     * Method to bind View Models
     * @param characterFavViewItemList List<CharacterFavViewItem>
     */
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
