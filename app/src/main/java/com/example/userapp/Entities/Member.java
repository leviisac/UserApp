package com.example.userapp.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import Interfaces.MyEntity;

@Entity(tableName = "members_table")
public class Member implements MyEntity {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private long Id;
    @ColumnInfo(name = "name")
    private String Name;
    @ColumnInfo(name = "lastname")
    private String Last_name;
    @ColumnInfo(name = "phone")
    private String Phone;
    @ColumnInfo(name = "address")
    private String Adress;


    public Member(Long id2 ,String name, String last_name, String phone, String adress) {
        Id = id2;
        Name = name;
        Last_name = last_name;
        Phone = phone;
        Adress = adress;
    }

    public Member(String name, String last_name, String phone, String adress) {
        Name = name;
        Last_name = last_name;
        Phone = phone;
        Adress = adress;
    }

    public Member() {
    }

    public String getcompleteName() {
        return (Name+" "+Last_name);
    }
    public long getId() {
        return Id;
    }

    public void setId(long id) {
        this.Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + Id +
                ", Name='" + Name + '\'' +
                ", Last_name='" + Last_name + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Adress='" + Adress + '\'' +
                '}';
    }
}
