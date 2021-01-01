package com.example.comicsappandroid.presentation.characterdisplay.search.fragment;

import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.comicsappandroid.R;
import com.example.comicsappandroid.data.di.FakeDependencyInjection;
import com.example.comicsappandroid.presentation.characterdisplay.search.adapter.CharacterActionInterface;
import com.example.comicsappandroid.presentation.characterdisplay.search.adapter.CharacterAdapterLinear;
import com.example.comicsappandroid.presentation.characterdisplay.search.adapter.CharacterAdapterGrid;
import com.example.comicsappandroid.presentation.characterdisplay.search.adapter.CharacterViewItem;
import com.example.comicsappandroid.presentation.characterinfodisplay.CharacterInfoDisplayActivity;
import com.example.comicsappandroid.presentation.viewmodel.FavoriteViewModel;
import com.example.comicsappandroid.presentation.viewmodel.SearchViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchFragment extends Fragment implements CharacterActionInterface {

    private SearchViewModel searchViewModel;
    private FavoriteViewModel favoriteViewModel;

    private RecyclerView recyclerView;
    private CharacterAdapterLinear characterAdapterLinear;
    private CharacterAdapterGrid characterAdapterGrid;
    private View rootView;

    private SearchView searchView;
    private ProgressBar progressBar;

    private FloatingActionButton fab;
    private boolean fabState;

    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

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
        setupFabButton();
    }

    private void refreshRecyclerViewForChanges() {
        searchViewModel.getEmptyList();
        if (searchView.getQuery().length() == 0) searchViewModel.searchCharacters("");
    }

    private void registerViewModels() {
        searchViewModel = new ViewModelProvider(requireActivity(),
                FakeDependencyInjection.getViewModelFactory()).get(SearchViewModel.class);

        favoriteViewModel = new ViewModelProvider(requireActivity(),
                FakeDependencyInjection.getViewModelFactory()).get(FavoriteViewModel.class);

        searchViewModel.getCharacters().observe(getViewLifecycleOwner(), new Observer<List<CharacterViewItem>>() {
            @Override
            public void onChanged(List<CharacterViewItem> characterViewItemList) {
                characterAdapterLinear.bindViewModels(characterViewItemList);
                characterAdapterGrid.bindViewModels(characterViewItemList);
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
        characterAdapterLinear = new CharacterAdapterLinear(this, getContext());
        characterAdapterGrid = new CharacterAdapterGrid(this, getContext());

        linearLayoutManager = new LinearLayoutManager(getContext());
        gridLayoutManager = new GridLayoutManager(getContext(),2);

        recyclerView.setAdapter(characterAdapterLinear);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setupSearchView() {
        searchView = rootView.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            private Timer queryTimer = new Timer();

            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewModel.searchCharacters("name:"+query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                if (newText.length() == 0) {
                    searchViewModel.cancelSubscription();
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
        if (isFav) favoriteViewModel.addToFavorite(characterID);
        else favoriteViewModel.deleteFromFavorite(characterID);
    }

    @Override
    public void startInfoActivity() {
        Intent intent = new Intent(getActivity(), CharacterInfoDisplayActivity.class);
        // Put Extras here
        startActivity(intent);
    }

    public void setupFabButton() {
        fab = rootView.findViewById(R.id.floatingActionButtonChangeGrid);
        fabState = false;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("FAB", "onClick: "+fabState);
                if(fabState) {
                    fab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_fab_list));
                    recyclerView.setAdapter(characterAdapterLinear);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
                else {
                    Log.d("FAB", "onClick: GRIID");
                    fab.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_fab_grid));
                    recyclerView.setAdapter(characterAdapterGrid);
                    recyclerView.setLayoutManager(gridLayoutManager);
                }
                fabState = !fabState; // Change fabState
                refreshRecyclerViewForChanges();
            }
        });
    }

    /**
     * Method to fix the problem of item added back to favorite when switching back to search
     */
    @Override
    public void onStart() {
        super.onStart();
        refreshRecyclerViewForChanges();
    }
}