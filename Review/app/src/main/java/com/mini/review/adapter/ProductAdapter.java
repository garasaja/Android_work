package com.mini.review.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mini.review.R;
import com.mini.review.databinding.ContainerItemBinding;
import com.mini.review.databinding.MenuItemBinding;
import com.mini.review.model.Product;
import com.mini.review.model.SearchKeyword;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{

    private static final String TAG = "ProductAdapter";

    private List<Product> products = new ArrayList<>();

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContainerItemBinding containerItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.container_item,parent,false);
        return new ProductHolder(containerItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = products.get(position);
        holder.containerItemBinding.setProduct(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> products) {
        Log.d(TAG, "setProducts: ");
        this.products = products;
        
    }

    public void setProduct(Product product) {
        this.products.add(product);
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private ContainerItemBinding containerItemBinding;

        public ProductHolder(@NonNull ContainerItemBinding containerItemBinding) {
            super(containerItemBinding.getRoot());
            this.containerItemBinding = containerItemBinding;
        }
    }
}
