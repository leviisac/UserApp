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
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapp.DAL.DB;

import com.example.userapp.DAL.MemberName;
import com.example.userapp.Entities.Member;
import com.example.userapp.R;
import com.example.userapp.ui.Adapter.MemberAdapter;


import java.util.List;

import Helper.ConsoleColors;
import Interfaces.NotifyDataChange;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private RecyclerView memberView;
    private List<Member> members;
    private NotifyDataChange<List<Member>> dataChangeListener;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //String name = getArguments().getString("params");
        //System.out.println(name);
        //String memberaddress= MemberName.getMemberAddress();
        //System.out.println(memberaddress);

        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);




        initParcelView(root);

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

        galleryViewModel.getAllMembers().observe(this, new Observer<List<Member>>() {
            @Override
            public void onChanged(@Nullable final List<Member> m) {
                // Update the cached copy of the words in the adapter.

                members = m;
                memberView.setAdapter(new MemberAdapter(getContext(), members));

            }
        });


    /*
        dataChangeListener = DB.getInstance().notifyParcelChange(new NotifyDataChange<List<Parcel>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void OnDataChanged(List<Parcel> list) {
                //aviso no console...
                System.out.println("SlideshowFragment()->dataChangeListener:OnDataChanged()");
                list.forEach(System.out::println);
                ConsoleColors.printYellowLn("SlideshowFragment()->dataChangeListener:OnDataChanged() END");


                if (parcelView.getAdapter() == null) {
                    parcels = list;
                    parcelView.setAdapter(new ParcelAdapter(getContext(), parcels));
                } else
                    parcelView.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onFailure(Exception exception) {
                Toast.makeText(getContext(), "error to get parcels list\n" + exception.toString(), Toast.LENGTH_LONG).show();
            }
        });

     */
    }


}


