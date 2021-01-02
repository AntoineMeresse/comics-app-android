package com.example.comicsappandroid.presentation.characterdisplay.favorite.fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comicsappandroid.R;
import com.example.comicsappandroid.data.di.FakeDependencyInjection;
import com.example.comicsappandroid.presentation.characterdisplay.favorite.adapter.CharacterFavActionInterface;
import com.example.comicsappandroid.presentation.characterdisplay.favorite.adapter.CharacterFavAdapter;
import com.example.comicsappandroid.presentation.characterdisplay.favorite.adapter.CharacterFavViewItem;
import com.example.comicsappandroid.presentation.viewmodel.FavoriteViewModel;

import java.util.List;

/**
 * Favorite Fragment
 */
public class FavoriteFragment extends Fragment implements CharacterFavActionInterface {

    private FavoriteViewModel favoriteViewModel;
    private View rootView;

    private CharacterFavAdapter characterFavAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.favorite_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        registerViewModel();
    }

    /**
     * Method to setup the favorite Recycler view
     */
    private void setupRecyclerView() {
        this.recyclerView = rootView.findViewById(R.id.recycler_view_favorite);
        this.characterFavAdapter = new CharacterFavAdapter(this, getContext());
        this.linearLayoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setAdapter(characterFavAdapter);
        this.recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void removeFromFavorite(String id) {
        favoriteViewModel.deleteFromFavorite(id);
    }

    /**
     * Method to register the Favorite VIew model
     */
    private void registerViewModel(){
        favoriteViewModel = new ViewModelProvider(requireActivity(),
                FakeDependencyInjection.getViewModelFactory()).get(FavoriteViewModel.class);

        favoriteViewModel.getFavs().observe(getViewLifecycleOwner(), new Observer<List<CharacterFavViewItem>>() {
            @Override
            public void onChanged(List<CharacterFavViewItem> characterFavViewItemList) {
                characterFavAdapter.bindViewModels(characterFavViewItemList);
            }
        });
    }
}