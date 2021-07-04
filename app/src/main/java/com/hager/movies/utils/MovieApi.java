package com.hager.movies.utils;

import com.hager.movies.models.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
  @GET("/3/movie/popular?api_key=4561c1c943be2d52c0c5b00d67fd38ad")
  Call<MovieModel> getTrendingMovies();

  @GET("/3/movie/now_playing?api_key=4561c1c943be2d52c0c5b00d67fd38ad")
  Call<MovieModel> getRecentMovies();

  @GET("/3/movie/popular?api_key=4561c1c943be2d52c0c5b00d67fd38ad")
  Call<MovieModel> getPageOfMovies(@Query("page") int page_num);

  @GET("/3/search/movie?api_key={api_key}&query={movie_name}")
   Call<List<MovieModel>> getSearchedMovie(@Path("api_key" )String  api_key
          ,@Path("movie_name") String movie_name);

  @GET("/3/movie/{movie_id}?api_key={api_key}")
   Call<MovieModel> getMovieDetails(@Path("movie_id") int movie_id
           , @Path("api_key") String api_key);


}
