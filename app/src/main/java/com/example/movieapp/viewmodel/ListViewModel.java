package com.example.movieapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.model.MovieModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {
    
    
    public MutableLiveData<List<MovieModel>> movies=new MutableLiveData<List<MovieModel>>();
    public MutableLiveData<Boolean>movieLoadError=new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading=new MutableLiveData<Boolean>();
    
    public void refresh(){
        fetchData();
    }

    private void fetchData() {
        MovieModel movie1=new MovieModel("The Godfather",8.80);
        MovieModel movie2=new MovieModel("The Shawshank Redemption",8.20);
        MovieModel movie3=new MovieModel("Parasite",8.80);

        List<MovieModel> list=new ArrayList<>();
        list.add(movie1);
        list.add(movie2);
        list.add(movie3);

        movies.setValue(list);
        movieLoadError.setValue(false);
        loading.setValue(false);
    }

}
