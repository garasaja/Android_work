package com.mini.recyclerviewex01;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{

    private static final String TAG = "MovieAdapter";
    private List<Movie> items = new ArrayList<>();

    public void addItem(Movie movie) {
        items.add(movie);
    }


    //껍데기만 만들어줌 데이터는 x
    @NonNull
    @Override // 초기생성 view
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item, parent,false);
        return new MyViewHolder(view);
    }

    //껍데기에 데이터 바인딩 해줌
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = items.get(position);
        holder.setItem(movie);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //ViewHolder (뷰들의 책꽃이)
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        //규칙 1
        private TextView tvTitle;
        private ImageView ivImgResource;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //규칙2
            tvTitle = itemView.findViewById(R.id.tv_title);
            ivImgResource = itemView.findViewById(R.id.iv_img_resource);
        }

        //규칙3
        public void setItem(Movie movie) {
            tvTitle.setText(movie.getTitle());
            ivImgResource.setImageResource(movie.getImgResource());
        }
    }
}
