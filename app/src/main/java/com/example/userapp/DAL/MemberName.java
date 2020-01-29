package com.example.userapp.DAL;

import com.example.userapp.Entities.Member;

public class MemberName {

    public static void setAddress(String address) {
        MemberName.address = address;
    }

    private static String address;


    public static String getMemberAddress()
    {

        return address;
    }
}
