package Interfaces;


import android.view.View;

import androidx.annotation.NonNull;

import com.example.userapp.ui.ViewHolder.ParcelViewHolder;

public interface OnItemClickListener {

void onItemClick(View view,int position ,boolean inLongClick);

}
