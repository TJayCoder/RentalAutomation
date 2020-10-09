package com.example.rentalautomation;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.productViewHolder>{

    private Context mCtx;

    private List<Product>productList;

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        productViewHolder holder = new productViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull productViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textViewTittle.setText(product.getTittle());
        holder.textViewDesc.setText(product.getShortdesc());
        holder.textViewPrice.setText(String.valueOf (product.getPrice()));

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class productViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTittle, textViewDesc, textViewPrice;

        public productViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTittle = itemView.findViewById(R.id.textViewTittle);
            textViewDesc = itemView.findViewById(R.id.textViewShort);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }


}
