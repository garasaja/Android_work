package com.mini.review.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mini.review.R;
import com.mini.review.databinding.MenuItemBinding;
import com.mini.review.model.Product;
import com.mini.review.model.SearchKeyword;

import java.util.ArrayList;
import java.util.List;

public class SearchKeywordAdapter extends RecyclerView.Adapter<SearchKeywordAdapter.SearchKeywordHolder>{

    private List<SearchKeyword> searchKeywords = new ArrayList<>();

    @NonNull
    @Override
    public SearchKeywordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MenuItemBinding menuItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.menu_item,parent,false);

        return new SearchKeywordHolder(menuItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchKeywordHolder holder, int position) {
        SearchKeyword searchKeyword = searchKeywords.get(position);
        holder.menuItemBinding.setSearchKeyword(searchKeyword);
    }

    @Override
    public int getItemCount() {
        return searchKeywords.size();
    }

    public void setSearchKeywords(List<SearchKeyword> searchKeywords) {
        this.searchKeywords = searchKeywords;
    }

    class SearchKeywordHolder extends RecyclerView.ViewHolder {
        private MenuItemBinding menuItemBinding;

        public SearchKeywordHolder(@NonNull MenuItemBinding menuItemBinding) {

            super(menuItemBinding.getRoot());
            this.menuItemBinding = menuItemBinding;
        }

    }
}
