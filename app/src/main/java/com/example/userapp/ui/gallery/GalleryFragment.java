package com.example.userapp.ui.gallery;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapp.DAL.DB;
import com.example.userapp.Entities.Members;
import com.example.userapp.R;
import com.example.userapp.ui.Adapter.MemberAdapater;
import com.example.userapp.ui.gallery.GalleryViewModel;

import java.util.List;

import Helper.ConsoleColors;
import Interfaces.NotifyDataChange;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView membersView;
    private List<Members> members;
    private NotifyDataChange<List<Members>> dataChangeListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);


        return root;
    }

    private void initParcelView(View view) {
        membersView = view.findViewById(R.id.membersRecycleView);
        membersView.setHasFixedSize(true);
        membersView.setLayoutManager(new LinearLayoutManager(getContext()));
        setMembersListener();
    }

    private void setMembersListener() {
        //Set listener




        dataChangeListener = DB.getInstance().notifyMembersChange(new NotifyDataChange<List<Members>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void OnDataChanged(List<Members> list) {
                //aviso no console...
                System.out.println("SlideshowFragment()->dataChangeListener:OnDataChanged()");
                list.forEach(System.out::println);
                ConsoleColors.printYellowLn("SlideshowFragment()->dataChangeListener:OnDataChanged() END");


                //if (membersView.getAdapter() == null) {
                    members = list;
                    membersView.setAdapter(new MemberAdapater (getContext(), members));
               // } else
                  //  membersView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onFailure(Exception exception) {
                Toast.makeText(getContext(), "error to get members list\n" + exception.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}