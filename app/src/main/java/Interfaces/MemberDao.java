package Interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.lifecycle.LiveData;

import com.example.userapp.Entities.Member;

import java.util.List;

@Dao
public interface MemberDao {
    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Member member);

    @Query("DELETE FROM members_table")
    void deleteAll();

    @Query("SELECT * from members_table ")
    LiveData<List<Member>> getAllMembers();


}
