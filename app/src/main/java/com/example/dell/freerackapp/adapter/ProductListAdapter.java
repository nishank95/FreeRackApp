package com.example.dell.freerackapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.freerackapp.R;
import com.example.dell.freerackapp.model.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private Context mContext;
    private List<Product> mProductList;

  ProductListAdapter(Context context, List<Product> products) {
      mContext = context;
      mProductList = products;
  }
    public ProductListAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.product_list_item,parent,false);
        return new ProductListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_name)TextView productName;
        @BindView(R.id.product_location)TextView productLocation;
        @BindView(R.id.product_category)TextView productCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
