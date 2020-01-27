package com.example.userapp.Entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Entity;

import Interfaces.MyEntity;

@Entity(tableName = "parcel_table")
public class Parcel implements MyEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private long Id;

    @ColumnInfo(name = "location")
    private String Location;
    @ColumnInfo(name = "type")
    private String Type;
    @ColumnInfo(name = "weight")
    private String Weight;
    @ColumnInfo(name = "fragile")
    private boolean Fragile;
    @ColumnInfo(name = "name")
    private String Name;

    @ColumnInfo(name = "address")
    private String Address;
    @ColumnInfo(name = "senddate")
    private String SendDate;
    @ColumnInfo(name = "expecteddate")
    private String ExpectedDate;
    @ColumnInfo(name = "phonenumber")
    private String PhoneNumber;
    @ColumnInfo(name = "email")
    private String Email;


    public void setId(long id) {
        this.Id = id;
    }
    public void setLocation(String location) {
        Location = location;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public void setFragile(boolean fragile) {
        Fragile = fragile;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setSendDate(String sendDate) {
        SendDate = sendDate;
    }

    public void setExpectedDate(String expectedDate) {
        ExpectedDate = expectedDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLocation() {
        return Location;
    }

    public String getType() {
        return Type;
    }

    public String getWeight() {
        return Weight;
    }

    public boolean isFragile() {
        return Fragile;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getSendDate() {
        return SendDate;
    }

    public String getExpectedDate() {
        return ExpectedDate;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }
    public long getId() {
        return Id;
    }


    public Parcel(){

    }
    public Parcel(String Id, String location, String type, String weight, boolean fragile, String name, String address, String sendDate, String expectedDate, String phoneNumber, String email) {
        Location = location;
        Type = type;
        Weight = weight;
        Fragile = fragile;
        Name = name;
        Address = address;
        SendDate = sendDate;
        ExpectedDate = expectedDate;
        PhoneNumber = phoneNumber;
        Email = email;

    }

    @Override
    public String toString() {
        return "Parcel{" +
                "Id='" + Id + '\'' +
                ", Location='" + Location + '\'' +
                ", Type='" + Type + '\'' +
                ", Weight='" + Weight + '\'' +
                ", Fragile=" + Fragile +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", SendDate='" + SendDate + '\'' +
                ", ExpectedDate='" + ExpectedDate + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}