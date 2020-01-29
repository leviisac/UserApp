package com.example.userapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.userapp.DAL.MemberRepository;
import com.example.userapp.Entities.Member;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MemberRepository mRepository;
    private Member mMember;
    String MemberName;

    public MainViewModel(Application app) {
        super(app);
        mRepository = new MemberRepository(app);

    }

    public void SetMemberName(String name)
    {
        MemberName=name;
        //mMember = mRepository.getMember(MemberName);
    }
    public Member GetMember()
    {
        return  mMember;
    }


    public void insert(Member m) { mRepository.insert(m); }

}

