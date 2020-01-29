package com.example.userapp.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userapp.DAL.MemberName;
import com.example.userapp.DAL.MemberRepository;
import com.example.userapp.Entities.Member;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    //private MutableLiveData<String> mText;

    private MemberRepository mRepository;
    private LiveData<List<Member>> mMember;
    private LiveData<List<Member>> mAllMembers;


    public HomeViewModel(Application app) {
        super(app);
        //mText = new MutableLiveData<>();
        //mText.setValue("This is home fragment");

        mRepository = new MemberRepository(app);
        mMember = mRepository.getMember(MemberName.getMemberAddress());
        mAllMembers = mRepository.getAllMembers();
    }

    LiveData<List<Member>> getMember() { return mMember; }

    LiveData<List<Member>> getAllMembers() { return mAllMembers; }

    //public LiveData<String> getText() {
        //return mText;
    //}
}