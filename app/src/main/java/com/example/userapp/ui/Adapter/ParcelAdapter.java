package com.example.userapp.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapp.Entities.Parcel;
import com.example.userapp.R;
import com.example.userapp.ui.ViewHolder.ParcelViewHolder;

import java.util.List;

public class ParcelAdapter extends RecyclerView.Adapter<ParcelViewHolder>{


    private Context baseContext;
    private List<Parcel> parcels;

    public ParcelAdapter(Context baseContext, List<Parcel> parcels) {
        this.baseContext = baseContext;
        this.parcels = parcels;
    }

    @NonNull
    @Override
    public ParcelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(baseContext.getApplicationContext()).inflate(R.layout.packet_item, parent, false);
        return new ParcelViewHolder(v);
    }

    @Override
    public void onViewRecycled(@NonNull ParcelViewHolder holder) {
        super.onViewRecycled(holder);


    }
    @Override
    public void onBindViewHolder(@NonNull ParcelViewHolder holder, int position) {
        Parcel parcel = parcels.get(position);
        holder.setMainTitle(parcel.getAddress());
        holder.setSubTitle(parcel.getName());
        holder.setImage(R.drawable.packet);
    }

    @Override
    public int getItemCount() {
        return parcels.size();
    }
}
