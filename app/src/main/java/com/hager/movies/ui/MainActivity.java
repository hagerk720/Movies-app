package com.hager.movies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hager.movies.databinding.ActivityMainBinding;
import com.hager.movies.models.MovieModel;
import com.hager.movies.models.Result;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    ActivityMainBinding binding ;
    MovieViewModel movieViewModel ;
    ViewPagerAdapter viewPagerAdapter ;
    List<Result> Recent_movies ;
    List<Result> trending_movies ;
    MoviesAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.get_Recent_movies();

        movieViewModel.listMutableLiveData.observe(this, new Observer<MovieModel>() {
            @Override
            public void onChanged(MovieModel movieModel) {
                Recent_movies = movieModel.getResults() ;
                adapter= new MoviesAdapter(Recent_movies,getBaseContext());
                binding.recyclerViewRecentMovies.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
                binding.recyclerViewRecentMovies.setAdapter(adapter);

            }
        });
        movieViewModel.get_popular_movies();
        movieViewModel.listMutableLiveData.observe(this, new Observer<MovieModel>() {
            @Override
            public void onChanged(MovieModel movieModel) {
              trending_movies = movieModel.getResults();
              viewPagerAdapter = new ViewPagerAdapter(getBaseContext() , trending_movies);
              binding.viewPagerMain.setAdapter(viewPagerAdapter);
            }
        });

        binding.btnSeeMoreRecent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , ViewAllMoviesActivity.class);
                intent.putExtra("btn" , "recent");
                startActivity(intent);
            }
        });




    }
}