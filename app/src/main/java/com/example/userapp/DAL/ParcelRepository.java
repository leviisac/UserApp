package com.example.userapp.DAL;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.userapp.Entities.Parcel;

import java.util.List;

import Interfaces.ParcelDao;

public class ParcelRepository {

    private ParcelDao mParcelDao;
    private LiveData<List<Parcel>> mAllParcels;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public ParcelRepository(Application application) {
        ParcelRoomDB db = ParcelRoomDB.getDatabase(application);
        mParcelDao = db.parcelDao();
        mAllParcels = mParcelDao.getAllParcels();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Parcel>> getAllParcels() {
        return mAllParcels;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures

    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Parcel p) {
        ParcelRoomDB.databaseWriteExecutor.execute(() -> {
            mParcelDao.insert(p);
        });
    }
}