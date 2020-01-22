package com.example.userapp.ui.slideshow;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapp.DAL.DB;
import com.example.userapp.Entities.Parcel;
import com.example.userapp.R;
import com.example.userapp.ui.Adapter.ParcelAdapter;

import java.util.List;

import Helper.ConsoleColors;
import Interfaces.NotifyDataChange;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private RecyclerView parcelView;
    private List<Parcel> parcels;
    private NotifyDataChange<List<Parcel>> dataChangeListener;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        initParcelView(root);

        return root;
    }



    private void initParcelView(View view) {
        parcelView = view.findViewById(R.id.parcelRecycleView);
        parcelView.setHasFixedSize(true);
        parcelView.setLayoutManager(new LinearLayoutManager(getContext()));
        setParcelListener();
    }



    private void setParcelListener() {
        //Set listener
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
    }
}