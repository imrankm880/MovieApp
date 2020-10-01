package com.example.movieapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.model.MovieModel;
import com.example.movieapp.model.MovieService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {
    
    
    public MutableLiveData<List<MovieModel>> movies=new MutableLiveData<List<MovieModel>>();
    public MutableLiveData<Boolean>movieLoadError=new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading=new MutableLiveData<Boolean>();

    private MovieService movieService= MovieService.getInstance();
    private CompositeDisposable disposable = new CompositeDisposable();

    public void refresh(){
        fetchData();
    }

    private void fetchData() {
        loading.setValue(false);

        disposable.add(
                movieService.getMovies()
                        .subscribeOn(Schedulers.newThread())//enables a new thread
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<MovieModel>>() {

                            @Override
                            public void onSuccess(List<MovieModel> movieModels) {
                                movies.setValue(movieModels);
                                Log.d("TAG", "getInstance:  Onsuccess ${movieModels}");
                                movieLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                movieLoadError.setValue(true);
                                loading.setValue(false);
                                Log.d("TAG", "getInstance:  onFailure}");
                                e.printStackTrace();
                            }
                        })
        );




//        MovieModel movie1=new MovieModel("The Godfather",8.80);
//        MovieModel movie2=new MovieModel("The Shawshank ",8.20);
//        MovieModel movie3=new MovieModel("Parasite",8.80);
//        MovieModel movie4=new MovieModel("prison break",8.20);
//
//        List<MovieModel> list=new ArrayList<>();
//        list.add(movie1);
//        list.add(movie2);
//        list.add(movie3);
//        list.add(movie4);
//
//        movies.setValue(list);
//        movieLoadError.setValue(false);
//        loading.setValue(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

}
