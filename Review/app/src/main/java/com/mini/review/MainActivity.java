package com.mini.review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.mini.review.adapter.ProductAdapter;
import com.mini.review.adapter.SearchKeywordAdapter;
import com.mini.review.model.Product;
import com.mini.review.model.SearchKeyword;
import com.mini.review.viewmodel.SearchKeywordViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private MainActivity mContext = MainActivity.this;
    private RecyclerView rvContainer, rvMenu;
    private SearchKeywordAdapter searchKeywordAdapter;
    private ProductAdapter productAdapter;
    private SearchKeywordViewModel searchKeywordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initSetting();
       // sampleData();
        initData();
    }

    private void initData() {
        searchKeywordViewModel.데이터등록();
        searchKeywordViewModel.구독().observe(mContext, new Observer<List<SearchKeyword>>() {
            @Override
            public void onChanged(List<SearchKeyword> searchKeywords) {
                searchKeywordAdapter.setSearchKeywords(searchKeywords);
                searchKeywordAdapter.notifyDataSetChanged();
            }
        });
    }

    private void init() {
        rvMenu = findViewById(R.id.rv_menu);
        rvContainer = findViewById(R.id.rv_container);
        productAdapter = new ProductAdapter();
        searchKeywordAdapter = new SearchKeywordAdapter();
        searchKeywordViewModel = ViewModelProviders.of(mContext).get(SearchKeywordViewModel.class);
    }

    private void initSetting() {
        rvMenu.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.HORIZONTAL,false));
        rvContainer.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false));

        rvMenu.setAdapter(searchKeywordAdapter);
        rvContainer.setAdapter(productAdapter);
    }

    private void sampleData() {
        List<SearchKeyword> searchKeywords = new ArrayList<>();
        searchKeywords.add(new SearchKeyword(1,"갤럭시20"));
        searchKeywords.add(new SearchKeyword(2,"아이폰12"));
        searchKeywords.add(new SearchKeyword(3,"맥북프로"));
        searchKeywords.add(new SearchKeyword(4,"아이폰11"));
        searchKeywordAdapter.setSearchKeywords(searchKeywords);

        List<Product> products = new ArrayList<>();
        products.add(Product.builder().thumnail("http://www.yologuys.com/Escape_img/theme/1396.jpg").title("제목1").day("1일전").build());
        products.add(Product.builder().thumnail("http://www.yologuys.com/Escape_img/theme/1396.jpg").title("제목2").day("2일전").build());
        products.add(Product.builder().thumnail("http://www.yologuys.com/Escape_img/theme/1396.jpg").title("제목3").day("3일전").build());
        productAdapter.setProducts(products);
    }
}
