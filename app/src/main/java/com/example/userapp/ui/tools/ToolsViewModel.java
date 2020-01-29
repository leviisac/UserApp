package com.example.userapp.ui.tools;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userapp.DAL.MemberName;
import com.example.userapp.DAL.MemberRepository;
import com.example.userapp.Entities.Member;

import java.util.List;

public class ToolsViewModel extends AndroidViewModel {

   // private MutableLiveData<String> mText;

    private MemberRepository mRepository;
    private LiveData<List<Member>> mAllMembers;
    private LiveData<List<Member>> mMember;

    public ToolsViewModel(Application app) {
        super(app);
        //mText = new MutableLiveData<>();
        //mText.setValue("This is tools fragment");
        mRepository = new MemberRepository(app);
        mAllMembers = mRepository.getAllMembers();
        mMember = mRepository.getMember(MemberName.getMemberAddress());

    }

    //public LiveData<String> getText() {
        //return mText;
    //}
    LiveData<List<Member>> getAllMembers() { return mAllMembers; }
    LiveData<List<Member>> getMember() { return mMember; }
}