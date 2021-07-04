package com.hager.movies.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hager.movies.models.MovieModel;
import com.hager.movies.requests.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    MutableLiveData<MovieModel> listMutableLiveData = new MutableLiveData<>();

   public void get_popular_movies (){
        Service.getInstance().getPopularMoviesFromRetrofit().enqueue(
                new Callback<MovieModel>() {
                    @Override
                    public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                        if (response.body() == null){
                            Log.d("response :" ,"empty");
                        }
                        else{
                            Log.d("response " , "non empty");
                        }
                        listMutableLiveData.setValue(response.body());

                    }

                    @Override
                    public void onFailure(Call<MovieModel> call, Throwable t) {
                        Log.d("throw" , t.getMessage());

                    }
                }
        );
    }
   public void get_Recent_movies(){
       Service.getInstance().getRecentMoviesFromRetrofit().enqueue(new Callback<MovieModel>() {
           @Override
           public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
               listMutableLiveData.setValue(response.body());
           }

           @Override
           public void onFailure(Call<MovieModel> call, Throwable t) {

           }
       });
    }
    public void get_page_movies(int page_num){
       Service.getInstance().getPageMoviesFromRetrofit(page_num).enqueue(new Callback<MovieModel>() {
           @Override
           public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
               listMutableLiveData.setValue(response.body());
           }

           @Override
           public void onFailure(Call<MovieModel> call, Throwable t) {

           }
       });

    }
}
