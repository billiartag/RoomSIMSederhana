package com.example.roomsimsederhana;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAOMahasiswa {
    @Query("SELECT * FROM nilai_mahasiswa")
    List<ClassMahasiswa> getAllNilai();

    @Query("SELECT * FROM nilai_mahasiswa WHERE NRP=:NRP")
    ClassMahasiswa getSiswa(String NRP);

    @Update
    void updateNilai(ClassMahasiswa... nilai);
    @Insert
    void insertNilai(ClassMahasiswa... nilai);
    @Delete
    void deleteNilai(ClassMahasiswa... nilai);

}
