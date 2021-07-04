package com.hager.movies.requests;

import com.hager.movies.models.MovieModel;
import com.hager.movies.utils.Keys;
import com.hager.movies.utils.MovieApi;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {
    private static Service Instance ;
    private MovieApi movieApi ;

    public Service() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Keys.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
         movieApi = retrofit.create(MovieApi.class);
    }

    public static Service getInstance() {
        if(null == Instance){
            Instance = new Service();
        }
        return Instance;
    }
    public Call<MovieModel> getPopularMoviesFromRetrofit(){
        return movieApi.getTrendingMovies();
    }
    public Call<MovieModel> getRecentMoviesFromRetrofit(){
        return movieApi.getRecentMovies();
    }
    public Call<MovieModel> getPageMoviesFromRetrofit(int page_num){return  movieApi.getPageOfMovies(page_num);}
}
