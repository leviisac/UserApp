package com.example.userapp.ui.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapp.R;

import Interfaces.OnItemClickListener;

public class ParcelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

    private final View itemView;


    private OnItemClickListener itemClickListener;

    public ParcelViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }



    public void setItemClickListener(OnItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }



    public void onClick(View v)
    {
        itemClickListener.onItemClick(v,getAdapterPosition(),false);
    }

    public boolean onLongClick(View v)
    {
        return false;
        /*
        itemClickListener.onItemClick(v,getAdapterPosition(),true);
        return true
        * */
    }

    public String getMainTitle(){
        return String.valueOf(((TextView) itemView.findViewById(R.id.mainttext)).getText());
    }

    public void setMainTitle(String mainTitle){
        ((TextView) itemView.findViewById(R.id.mainttext)).setText(mainTitle);
    }

    public String getSubTitle(){
        return String.valueOf(((TextView) itemView.findViewById(R.id.subttext)).getText());
    }

    public void setSubTitle(String subTitle){
        ((TextView) itemView.findViewById(R.id.subttext)).setText(subTitle);
    }

    public void setImage(int source){
        ((ImageView)  itemView.findViewById(R.id.image)).setImageResource(source);
    }
}
