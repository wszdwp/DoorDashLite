package com.codingpan.doordashlite.activities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingpan.doordashlite.R;
import com.codingpan.doordashlite.objects.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {
    private static final String TAG = "RestaurantListAdapter";

    private List<Restaurant> restaurantList;

    public RestaurantListAdapter() {
        restaurantList = new ArrayList<>();
    }

    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_row, parent, false);
        return new RestaurantViewHolder(row);
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.fillContent(restaurantList.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView logoImageView;
        TextView nameTextView;
        TextView descTextView;
        TextView deliveryFeeTextView;
        TextView statusTextView;

        RestaurantViewHolder(View itemView) {
            super(itemView);
            logoImageView = itemView.findViewById(R.id.logo);
            nameTextView = itemView.findViewById(R.id.name);
            descTextView = itemView.findViewById(R.id.desc);
            deliveryFeeTextView = itemView.findViewById(R.id.deliveryFee);
            statusTextView = itemView.findViewById(R.id.status);
        }

        void fillContent(@NonNull Restaurant restaurant) {
            Picasso.with(itemView.getContext())
                    .load(restaurant.getCoverImageUrl())
                    .into(logoImageView);
            nameTextView.setText(restaurant.getName());
            descTextView.setText(restaurant.getDescription());
            deliveryFeeTextView.setText("$ " + restaurant.getDeliveryFee() + " delivery");
            statusTextView.setText(restaurant.getStatus());
        }
    }
}
