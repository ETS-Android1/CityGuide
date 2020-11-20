package com.example.cityguide.HelperClasses.Home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguide.R;

public class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView restName;
    public ImageView rest_image;

    private ItemClickListener itemClickListener;
    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    public RestaurantViewHolder(@NonNull View itemView) {
        super(itemView);
        this.restName = (TextView) itemView.findViewById(R.id.rest_title);
        this.rest_image = (ImageView) itemView.findViewById(R.id.rest_image);
        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
