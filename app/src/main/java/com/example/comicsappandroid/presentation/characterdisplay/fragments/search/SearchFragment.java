package com.example.comicsappandroid.presentation.characterdisplay.fragments.search;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.comicsappandroid.R;
import com.example.comicsappandroid.data.di.FakeDependencyInjection;
import com.example.comicsappandroid.presentation.viewmodel.SearchViewModel;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        registerViewModels();
    }

    private void registerViewModels() {
        searchViewModel = new ViewModelProvider(requireActivity(),
                FakeDependencyInjection.getViewModelFactory()).get(SearchViewModel.class);
    }

}