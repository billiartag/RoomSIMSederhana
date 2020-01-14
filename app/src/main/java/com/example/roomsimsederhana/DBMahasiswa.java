package com.example.roomsimsederhana;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {ClassMahasiswa.class},version = 1, exportSchema = false)
public abstract class DBMahasiswa extends RoomDatabase {
    public abstract DAOMahasiswa daoMahasiswa();
}
