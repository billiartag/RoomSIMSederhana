package com.example.roomsimsederhana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DosenInsert extends AppCompatActivity {

    EditText edNama, edNilai,edNRP;
    Spinner jurusan;
    Button btnInsert;
    DBMahasiswa dbMahasiswa;
    String jenis_intent;
    ClassMahasiswa obyek_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_insert);

        edNRP = findViewById(R.id.editTextInsertNRP);
        edNama = findViewById(R.id.editTextInsertNama);
        edNilai= findViewById(R.id.editTextInsertNilai);
        jurusan = findViewById(R.id.spinnerInsert);
        btnInsert= findViewById(R.id.buttonInsertNilai);

        dbMahasiswa = Room.databaseBuilder(getApplicationContext(),DBMahasiswa.class,"DBNilai").build();
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jur = jurusan.getSelectedItem().toString();
                int nilai = Integer.parseInt(edNilai.getText().toString());
                if(jenis_intent.equalsIgnoreCase("insert")) {
                    ClassMahasiswa temp = new ClassMahasiswa(0, edNRP.getText().toString(), edNama.getText().toString(), jur, nilai);
                    insert(temp);
                }
                else{
                    ClassMahasiswa temp = new ClassMahasiswa(obyek_update.getPk_nilai_mhs(), edNRP.getText().toString(), edNama.getText().toString(), jur, nilai);
                    insert(temp);
                }

                finish();
            }
        });

        Intent i = getIntent();
        jenis_intent=i.getStringExtra("jenis");
        if(i.getStringExtra("jenis").equalsIgnoreCase("update")){
            obyek_update = (ClassMahasiswa) i.getSerializableExtra("data");

            btnInsert.setText("Update");
            edNilai.setText(obyek_update.getNilai()+"");
            edNama.setText(obyek_update.getNama());
            edNRP.setText(obyek_update.getNRP()+"");

            String[] list_jurusan = getResources().getStringArray(R.array.list_jurusan);

            jurusan.setSelection(Arrays.asList(list_jurusan).indexOf(obyek_update.getJurusan()));
        }
    }
    private void insert(ClassMahasiswa nilai){
        if(jenis_intent.equalsIgnoreCase("insert")){
            new insertNilai().execute(nilai);
        }
        else{
            new updateNilai().execute(nilai);
        }

    }

    private class insertNilai extends AsyncTask<ClassMahasiswa,Void,Void> {
        @Override
        protected Void doInBackground(ClassMahasiswa... classMahasiswas) {
            dbMahasiswa.daoMahasiswa().insertNilai(classMahasiswas[0]);
            return null;
        }
    }
    private class updateNilai extends AsyncTask<ClassMahasiswa,Void,Void> {
        @Override
        protected Void doInBackground(ClassMahasiswa... classMahasiswas) {
            dbMahasiswa.daoMahasiswa().updateNilai(classMahasiswas[0]);
            return null;
        }
    }
}
