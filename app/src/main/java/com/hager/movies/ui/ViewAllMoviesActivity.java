package com.hager.movies.ui;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hager.movies.R;
import com.hager.movies.models.MovieModel;
import com.hager.movies.models.Result;

import java.util.Arrays;
import java.util.List;

public class ViewAllMoviesActivity extends AppCompatActivity {
    MovieViewModel movieViewModel ;
    Button btn_recent ;
    Button btn_trending ;
    EditText search_et ;
    RecyclerView recyclerView_movies ;
    MoviesAdapter adapter ;
    List<Result> movies = Arrays.asList(new Result[16]);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_all_movies);
        btn_recent = findViewById(R.id.btn_recent);
        btn_trending = findViewById(R.id.btn_trending);
        recyclerView_movies = findViewById(R.id.recyclerView_movies);

        search_et = findViewById(R.id.search_bar_view_all_movies_activity);

        Intent intent = getIntent();
        switch (intent.getStringExtra("btn")){
            case "recent":
            movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
            movieViewModel.get_page_movies(2);
            movieViewModel.listMutableLiveData.observe(this, new Observer<MovieModel>() {
                @Override
                public void onChanged(MovieModel movieModel) {
                    movies = movieModel.getResults();
                    adapter = new MoviesAdapter(movieModel.getResults(), getBaseContext());
                    recyclerView_movies.setLayoutManager(new GridLayoutManager(getBaseContext(), 2));
                    recyclerView_movies.setAdapter(adapter);
                }
            });
            btn_recent.getBackground().setColorFilter(ContextCompat.getColor(this, R.color.checked_button),
                    PorterDuff.Mode.MULTIPLY);

            break;
        }
    }
}