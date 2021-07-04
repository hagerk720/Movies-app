package com.hager.movies.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hager.movies.R;
import com.hager.movies.models.Result;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {
 Context context ;
List<Result> trendingMoviesList ;
 LayoutInflater mLayoutInflater;


    public ViewPagerAdapter(Context context , List<Result> trendingMoviesList ) {
        this.context =context ;
        this.trendingMoviesList = trendingMoviesList ;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return trendingMoviesList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = mLayoutInflater.inflate(R.layout.image_inner_view_pager, container, false);

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView_main);
        TextView movieName = itemView.findViewById(R.id.movie_name);

        // setting the image in the imageView

        Picasso.get().load("https://image.tmdb.org/t/p/original"+ trendingMoviesList.get(position).getPosterPath())
                .centerCrop()
                .fit()
                .into(imageView);
        movieName.setText(trendingMoviesList.get(position).getTitle());

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }
    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

}
