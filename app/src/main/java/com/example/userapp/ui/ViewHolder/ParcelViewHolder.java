package com.example.userapp.ui.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapp.R;

public class ParcelViewHolder extends RecyclerView.ViewHolder{

    private final View itemView;

    public ParcelViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
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
