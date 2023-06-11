package com.example.fooddelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    Context context;
    ArrayList<Product> list;
    private final RecyclerviewInterface recyclerViewInterface;

    public Myadapter(Context context, ArrayList<Product> list, RecyclerviewInterface recyclerViewInterface) {
        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.list,parent,false);
        return new MyViewHolder(v,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product product =list.get(position);
        holder.name.setText(product.getProductname());
        holder.price.setText(product.getProductprice());
        Picasso.get().load(list.get(position).getImage()).into(holder.image);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,price;
        ImageView image;
        Button order;

        public MyViewHolder(@NonNull View itemView, RecyclerviewInterface recyclerViewInterface) {
            super(itemView);
            name=itemView.findViewById(R.id.productname);
            price=itemView.findViewById(R.id.price);
            image=itemView.findViewById(R.id.productimage);
            order=itemView.findViewById(R.id.order);


            order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Myadapter.this.recyclerViewInterface != null){
                        int pos=getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            Myadapter.this.recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });

        }
    }
}
