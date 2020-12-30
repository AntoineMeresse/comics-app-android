package com.example.comicsappandroid.presentation.characterdisplay.fragments.search;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.comicsappandroid.R;
import com.example.comicsappandroid.data.di.FakeDependencyInjection;
import com.example.comicsappandroid.presentation.characterdisplay.fragments.search.adapter.CharacterActionInterface;
import com.example.comicsappandroid.presentation.characterdisplay.fragments.search.adapter.CharacterAdapter;
import com.example.comicsappandroid.presentation.characterdisplay.fragments.search.adapter.CharacterViewItem;
import com.example.comicsappandroid.presentation.viewmodel.SearchViewModel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchFragment extends Fragment implements CharacterActionInterface {

    private SearchViewModel searchViewModel;
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private View rootView;

    private SearchView searchView;
    private ProgressBar progressBar;

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
        progressBar = rootView.findViewById(R.id.progress_circular);

        setupSearchView();
        setupRecyclerView();
        registerViewModels();

        initSearchCharacters();
    }

    private void initSearchCharacters() {
        searchViewModel.searchCharacters("");
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

        searchViewModel.getIsDataLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                progressBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view_search);
        characterAdapter = new CharacterAdapter(this, getContext());
        recyclerView.setAdapter(characterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupSearchView() {
        searchView = rootView.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            private Timer queryTimer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                if (newText.length() == 0) {
                    searchViewModel.cancelSubscription();
                    initSearchCharacters();
                }
                else {
                    queryTimer.cancel();
                    queryTimer = new Timer();
                    int sleep = 1000;
                    queryTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            searchViewModel.searchCharacters("name:"+newText);
                        }
                    }, sleep);
                }
                return true;
            }
        }
        );
    }

    @Override
    public void onHeartClick(String characterID, boolean isFav) {
        Log.d("HEARTBUTTONCLICLED", "onHeartClick: "+characterID);
    }
}