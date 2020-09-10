package com.mini.movieapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class YtsAdapter extends RecyclerView.Adapter<YtsAdapter.MyViewHolder>{

    private static final String TAG = "YtsAdapter";
    private List<YtsData.MyData.Movie> movies = new ArrayList<>();


    public void addItems(List<YtsData.MyData.Movie> movies) {
        this.movies = movies;
    }

//    public void addItem(YtsData.MyData.Movie movie) {
//        movies.add(movie);
//    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View cardItemView = inflater.inflate(R.layout.card_item,parent,false);
        
        
        return new MyViewHolder(cardItemView);

        
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setItem(movies.get(position));

        Log.d(TAG, "onBindViewHolder: " + "데이터바인딩 : " + position);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    // 책꽃이 (View들을 채워두면 됨)
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPoster;
        private TextView tvTitle,tvRating;
        private RatingBar ratingBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvRating = itemView.findViewById(R.id.tv_rating);
            ratingBar = itemView.findViewById(R.id.rating_bar);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), getAdapterPosition()+"클릭됨", Toast.LENGTH_SHORT).show();
//                }
//            });
        }

        public void setItem(YtsData.MyData.Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvRating.setText(movie.getRating()+"");
            Picasso.get().load(movie.getMedium_cover_image()).into(ivPoster);
            ratingBar.setRating(movie.getRating()/2);
        }
    }
}
