package com.example.movieapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.movieapp.R;
import com.example.movieapp.model.MovieModel;
import com.example.movieapp.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.movieList)
    RecyclerView movieList;

    @BindView(R.id.list_error)
    TextView list_error;

    @BindView(R.id.loading_view)
    ProgressBar progressBar;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ListViewModel viewModel;
    private MovieListAdapter adapter=new MovieListAdapter(new ArrayList<MovieModel>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        viewModel=ViewModelProviders.of(this).get(ListViewModel.class);//give exact same instance of view when activity re created

        movieList.setLayoutManager(new LinearLayoutManager(this));
        movieList.setAdapter(adapter);

        observeViewModel();
    }

    private void observeViewModel() {

    viewModel.movies.observe(this, movieModels -> {

        if(movieModels!= null){
            movieList.setVisibility(View.VISIBLE);
            adapter.updateMovies(movieModels);
        }

    });


        viewModel.movieLoadError.observe(this, isError -> {
            if (isError != null) {
                list_error.setVisibility(isError ? View.GONE : View.GONE);
            }
        });



        viewModel.loading.observe(this, isLoading -> {
            if (isLoading != null) {
                progressBar.setVisibility(isLoading ? View.GONE : View.GONE);
            }
            if (isLoading) {
                list_error.setVisibility(View.GONE);
                movieList.setVisibility(View.GONE);
            }
        });




    }


}