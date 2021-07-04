package com.hager.movies.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hager.movies.R;
import com.hager.movies.models.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieHolder>  {

    Context context ;
    List<Result> movies;
  //  OnRecyclerViewClickListener listener ;

    public MoviesAdapter (List<Result> movies ,Context context){
        this.movies = movies ;
        this.context = context ;

    }


//    public MoviesAdapter (List<Result> movies , OnRecyclerViewClickListener listener){
//        this.movies = movies ;
//        this.listener = listener ;
//    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext() ;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_inner_recycler_view , null , false) ;
        MovieHolder MovieHolder = new MovieHolder(v);
        return MovieHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Result m = movies.get(position);
        Picasso.get().load("https://image.tmdb.org/t/p/original"+ m.getPosterPath())
                .centerCrop().fit()
                .into(holder.movie_poster);
        Log.d("path" ,m.getPosterPath());
       // Bitmap bmImg = BitmapFactory.decodeFile(movies.get(position).getPosterPath());
        //holder.movie_poster.setImageBitmap(bmImg);
        holder.movie_title.setText(m.getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder{
        TextView movie_title ;
        ImageView movie_poster ;

        public MovieHolder(@NonNull final View itemView) {
            super(itemView);
             movie_title = itemView.findViewById(R.id.movie_name);
             movie_poster=  itemView.findViewById(R.id.imageView_main);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

}
