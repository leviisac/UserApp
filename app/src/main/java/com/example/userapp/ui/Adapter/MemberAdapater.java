package com.example.userapp.ui.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapp.Entities.Members;

import com.example.userapp.R;
import com.example.userapp.ui.ViewHolder.MembersViewHolder;

import java.util.List;

public class MemberAdapater  extends RecyclerView.Adapter<MembersViewHolder>{

    private Context baseContext;
    private List<Members> members;

    public MemberAdapater(Context baseContext, List<Members> members) {
        this.baseContext = baseContext;
        this.members = members;
    }

    @NonNull
    @Override
    public MembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(baseContext.getApplicationContext()).inflate(R.layout.member_item, parent, false);
        return new MembersViewHolder(v);
    }

    @Override
    public void onViewRecycled(@NonNull MembersViewHolder holder) {
        super.onViewRecycled(holder);


    }
    public void onBindViewHolder(@NonNull MembersViewHolder holder, int position) {
        Members membersunit = members.get(position);
        holder.setMainTitle(membersunit.getcompleteName());
        holder.setSubTitle(membersunit.getPhone());
        holder.setImage(R.drawable.user1);
    }
    @Override
    public int getItemCount() {
        return members.size();
    }
}
