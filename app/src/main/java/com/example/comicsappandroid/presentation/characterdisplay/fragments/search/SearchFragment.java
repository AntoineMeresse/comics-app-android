package com.example.comicsappandroid.presentation.characterdisplay.fragments.search;

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
import com.example.comicsappandroid.presentation.characterdisplay.fragments.search.adapter.CharacterAdapter;
import com.example.comicsappandroid.presentation.characterdisplay.fragments.search.adapter.CharacterViewItem;
import com.example.comicsappandroid.presentation.viewmodel.SearchViewModel;

import java.util.List;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private View rootView;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.search_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setRecyclerView();
        registerViewModels();

        searchViewModel.searchCharacters();
    }

    private void registerViewModels() {
        searchViewModel = new ViewModelProvider(requireActivity(),
                FakeDependencyInjection.getViewModelFactory()).get(SearchViewModel.class);

        searchViewModel.getCharacters().observe(getViewLifecycleOwner(), new Observer<List<CharacterViewItem>>() {
            @Override
            public void onChanged(List<CharacterViewItem> characterViewItemList) {
                characterAdapter.bindViewModels(characterViewItemList);
            }
        });
    }

    private void setRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view_search);
        characterAdapter = new CharacterAdapter();
        recyclerView.setAdapter(characterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}