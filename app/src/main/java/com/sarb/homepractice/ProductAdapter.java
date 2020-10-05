package com.sarb.homepractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    List<ProductModel> product;
    Context context;

    public ProductAdapter(List<ProductModel> product, Context context) {
        this.product = product;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_products,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_name.setText("Name = "+product.get(position).getName());
        holder.txt_memory.setText("Memory = "+product.get(position).getMemory());
        holder.txt_price.setText("Price = "+product.get(position).getPrice());

        Picasso.get().load(product.get(position).image).into(holder.img_product);


    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_name,txt_price,txt_memory;
        ImageView img_product;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_productName);
        txt_price= itemView.findViewById(R.id.txt_productPrice);
        txt_memory= itemView.findViewById(R.id.txt_productMemory);
        img_product= itemView.findViewById(R.id.img_product);
    }
}
}
