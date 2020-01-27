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

import com.example.userapp.Entities.Parcel;
import com.example.userapp.ui.Adapter.ParcelAdapter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Helper.ConsoleColors;
import Interfaces.NotifyDataChange;
import Interfaces.ParcelDao;

@Database(entities = {Parcel.class}, version = 1, exportSchema = false)
public abstract class ParcelRoomDB extends RoomDatabase {


    public abstract ParcelDao parcelDao();

    private static volatile ParcelRoomDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ParcelRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ParcelRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ParcelRoomDB.class, "parcel_database")
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
                ParcelDao dao = INSTANCE.parcelDao();
                dao.deleteAll();

                Parcel test = new Parcel("aaa","aaa","aaa","aaa", true, "aaa","aaa","aaa","aaa","aaa","aaa" );
                dao.insert(test);

                NotifyDataChange<List<Parcel>> dataChangeListener;
                dataChangeListener = DB.getInstance().notifyParcelChange(new NotifyDataChange<List<Parcel>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void OnDataChanged(List<Parcel> list) {
                        //aviso no console...
                        System.out.println("SlideshowFragment()->dataChangeListener:OnDataChanged()");
                        list.forEach(System.out::println);
                        ConsoleColors.printYellowLn("SlideshowFragment()->dataChangeListener:OnDataChanged() END");


                        for(Parcel p : list)
                        {
                            dao.insert(p);
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
