package com.example.userapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapp.DAL.MemberName;
import com.example.userapp.Entities.Member;
import com.example.userapp.R;
import com.example.userapp.ui.Adapter.MemberAdapter;

import java.util.List;

import Interfaces.NotifyDataChange;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView memberView;
    private List<Member> member;
    private NotifyDataChange<List<Member>> dataChangeListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //TextView textView = (TextView)root.findViewById(R.id.text_home);

        //String strtext=getArguments().getString("name");
        //textView.setText("AAAAAAAAAAAAA");

        /*
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

         */
        TextView textView2 = (TextView)root.findViewById(R.id.textView2);
        textView2.setText(MemberName.getMemberAddress());

        //initParcelView(root);

        return root;
    }

    private void initParcelView(View view) {
        memberView = view.findViewById(R.id.membersRecycleView);
        memberView.setHasFixedSize(true);
        memberView.setLayoutManager(new LinearLayoutManager(getContext()));
        setParcelListener();
    }



    private void setParcelListener() {
        //Set listener

        homeViewModel.getMember().observe(this, new Observer<List<Member>>() {
            @Override
            public void onChanged(@Nullable final List<Member> m) {
                // Update the cached copy of the words in the adapter.

                member = m;
                memberView.setAdapter(new MemberAdapter(getContext(), member));

            }
        });
    }
}