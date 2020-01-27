package com.example.userapp.ui.gallery;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.userapp.DAL.MemberRepository;
import com.example.userapp.Entities.Member;

import java.util.List;

public class GalleryViewModel extends AndroidViewModel {




    private MemberRepository mRepository;
    private LiveData<List<Member>> mAllMembers;

    public GalleryViewModel(Application app) {
        super(app);
        mRepository = new MemberRepository(app);
        mAllMembers = mRepository.getAllMembers();
    }

    LiveData<List<Member>> getAllMembers() { return mAllMembers; }

    public void insert(Member m) { mRepository.insert(m); }
}