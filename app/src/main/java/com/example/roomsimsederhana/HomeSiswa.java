package com.example.roomsimsederhana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class HomeSiswa extends AppCompatActivity {

    DBMahasiswa dbMahasiswa;
    ClassMahasiswa mhs;
    TextView edEval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_siswa);

        dbMahasiswa = Room.databaseBuilder(getApplicationContext(),DBMahasiswa.class,"DBNilai").build();

        edEval = findViewById(R.id.textViewNilaiSiswa);
        getDetail(getIntent().getStringExtra("nrp"));

    }
    private void getDetail(String NRP){
        new getNilai().execute(NRP);
    }

    private class getNilai extends AsyncTask<String,Void,Void> {
        @Override
        protected Void doInBackground(String... strings) {
            mhs = dbMahasiswa.daoMahasiswa().getSiswa(strings[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setData(mhs);
        }
    }

    private void setData(ClassMahasiswa mhs) {
        int nilai = mhs.getNilai();
        String grade = "";
        if(nilai<60){grade="C";}
        else if(nilai<80){grade="B";}
        else{grade="A";}
        edEval.setText("Welcome, "+mhs.getNama()+"\nNilai kamu "+grade);
    }
}
