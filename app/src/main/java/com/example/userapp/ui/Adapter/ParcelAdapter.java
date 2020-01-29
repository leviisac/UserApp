package com.example.userapp.ui.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapp.Entities.Parcel;
import com.example.userapp.R;
import com.example.userapp.ui.ViewHolder.ParcelViewHolder;
import com.example.userapp.ui.parcel_details;

import java.util.List;

import Interfaces.OnItemClickListener;

public class ParcelAdapter extends RecyclerView.Adapter<ParcelViewHolder> {


    private Context baseContext;
    private List<Parcel> parcels;


    public ParcelAdapter(Context baseContext, List<Parcel> parcels) {
        this.baseContext = baseContext;
        this.parcels = parcels;

    }


    public ParcelAdapter(DialogInterface.OnClickListener listener) {
        baseContext.startActivity(new Intent(this.baseContext, parcel_details.class));
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

        holder.setItemClickListener(new OnItemClickListener(){

            @Override
            public void onItemClick(View view, int position, boolean isLongClick) {
               // if(isLongClick)
                    //erase?
                //else
                Intent intent= new Intent(baseContext,parcel_details.class);
                intent.putExtra("parcel",parcel.getId());
                intent.putExtra("adress",parcel.getAddress());
                intent.putExtra("name",parcel.getName());
                baseContext.startActivity(intent);

            }
        });
    }



    @Override
    public int getItemCount() {
        return parcels.size();
    }


    public interface ListItemClickListener {

        void onItemClick(int position);

    }
}
