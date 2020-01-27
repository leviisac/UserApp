package Interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.userapp.Entities.Parcel;

import java.util.List;

@Dao
public interface ParcelDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Parcel p);

    @Query("DELETE FROM parcel_table")
    void deleteAll();

    @Query("SELECT * from parcel_table ")
    LiveData<List<Parcel>> getAllParcels();

}
