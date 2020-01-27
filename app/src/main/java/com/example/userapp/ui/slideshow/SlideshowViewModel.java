package com.example.userapp.ui.slideshow;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userapp.DAL.ParcelRepository;
import com.example.userapp.Entities.Parcel;

import java.util.List;

public class SlideshowViewModel extends AndroidViewModel {




    private ParcelRepository mRepository;
    private LiveData<List<Parcel>> mAllParcels;

    public SlideshowViewModel(Application app) {
        super(app);
        mRepository = new ParcelRepository(app);
       mAllParcels = mRepository.getAllParcels();
    }

    LiveData<List<Parcel>> getAllParcels() { return mAllParcels; }

    public void insert(Parcel p) { mRepository.insert(p); }
}