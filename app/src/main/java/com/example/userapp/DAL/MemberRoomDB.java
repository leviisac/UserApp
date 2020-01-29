package com.example.userapp.DAL;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.userapp.Entities.Member;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Helper.ConsoleColors;
import Interfaces.MemberDao;
import Interfaces.NotifyDataChange;


@Database(entities = {Member.class}, version = 1, exportSchema = false)
public abstract class MemberRoomDB extends RoomDatabase {


    public abstract MemberDao memberDao();

    private static volatile MemberRoomDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MemberRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MemberRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MemberRoomDB.class, "member_database")
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                MemberDao dao = INSTANCE.memberDao();
                dao.deleteAll();

                //Member test = new Member(new Long(1),"aaa","aaa", "aaa", "aaa");
                //dao.insert(test);

                NotifyDataChange<List<Member>> dataChangeListener;
                dataChangeListener = DB.getInstance().notifyMembersChange(new NotifyDataChange<List<Member>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void OnDataChanged(List<Member> list) {
                        //aviso no console...
                        System.out.println("SlideshowFragment()->dataChangeListener:OnDataChanged()");
                        list.forEach(System.out::println);
                        ConsoleColors.printYellowLn("SlideshowFragment()->dataChangeListener:OnDataChanged() END");


                        for(Member m : list)
                        {
                            dao.insert(m);
                        }

                    }

                    @Override
                    public void onFailure(Exception exception) {
                        //Toast.makeText(getContext(), "error to get parcels list\n" + exception.toString(), Toast.LENGTH_LONG).show();
                    }
                });

            });
        }
    };
}
