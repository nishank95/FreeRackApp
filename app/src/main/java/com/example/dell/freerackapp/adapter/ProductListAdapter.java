package com.example.dell.freerackapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.freerackapp.DetailActivity;
import com.example.dell.freerackapp.R;
import com.example.dell.freerackapp.model.Product;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private Context mContext;
    private List<Product> mProductList;
    private List<String> mProductName;
    private List<String> mProductLoc;
    private List<String> mProductCat;
    private List<String> mProductDesc;


    @BindView(R.id.prod_item_card)CardView prod_Card;

  public ProductListAdapter(Context context, List<String> name, List<String> loc, List<String> cat, List<String> des){
      mContext = context;
//      mProductList = products;
      mProductName = name;
      mProductLoc =loc;
      mProductCat =cat;
      mProductDesc =des;
  }


    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.product_list_item,parent,false);
        return new ProductListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
      holder.productName.setText(mProductName.get(position));
      holder.productLocation.setText(mProductLoc.get(position));
      holder.productCategory.setText(mProductCat.get(position));
    }

    @Override
    public int getItemCount() {
        return mProductName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.product_name)TextView productName;
        @BindView(R.id.product_location)TextView productLocation;
        @BindView(R.id.product_category)TextView productCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.prod_item_card)
        void onClick(View v){
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("name",mProductName.get(getAdapterPosition()));
            intent.putExtra("loc", mProductLoc.get(getAdapterPosition()));
            intent.putExtra("cat", mProductCat.get(getAdapterPosition()));
            intent.putExtra("des", mProductDesc.get(getAdapterPosition()));
            mContext.startActivity(intent);
        }
    }
}
