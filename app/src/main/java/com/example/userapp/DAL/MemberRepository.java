package com.example.userapp.DAL;

import android.app.Application;

import androidx.lifecycle.LiveData;


import com.example.userapp.Entities.Member;

import java.util.List;

import Interfaces.MemberDao;


public class MemberRepository {

    private MemberDao mMemberDao;
    private LiveData<List<Member>> mAllMembers;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public MemberRepository(Application application) {
        MemberRoomDB db = MemberRoomDB.getDatabase(application);
        mMemberDao = db.memberDao();
        mAllMembers = mMemberDao.getAllMembers();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Member>> getAllMembers() {
        return mAllMembers;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures

    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Member m) {
        ParcelRoomDB.databaseWriteExecutor.execute(() -> {
            mMemberDao.insert(m);
        });
    }
}
