package com.example.roomsimsederhana;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nilai_mahasiswa")
public class ClassMahasiswa {
    @PrimaryKey(autoGenerate = true)
    int pk_nilai_mhs;
    @ColumnInfo(name = "NRP")
    String NRP;
    @ColumnInfo(name = "Nama")
    String Nama;
    @ColumnInfo(name = "Jurusan")
    String Jurusan;
    @ColumnInfo(name = "Nilai")
    int Nilai;

    public ClassMahasiswa(int pk_nilai_mhs, String NRP, String Nama, String Jurusan, int Nilai) {
        this.pk_nilai_mhs = pk_nilai_mhs;
        this.NRP = NRP;
        this.Nama = Nama;
        this.Jurusan = Jurusan;
        this.Nilai = Nilai;
    }

    @Override
    public String toString() {
        return NRP+" - "+Nama+" - "+Jurusan+" - "+Nilai;
    }

    public String getNRP() {
        return NRP;
    }

    public void setNRP(String NRP) {
        this.NRP = NRP;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getJurusan() {
        return Jurusan;
    }

    public void setJurusan(String jurusan) {
        Jurusan = jurusan;
    }

    public int getNilai() {
        return Nilai;
    }

    public void setNilai(int nilai) {
        Nilai = nilai;
    }
}
