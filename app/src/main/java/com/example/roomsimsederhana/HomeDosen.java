package com.example.roomsimsederhana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HomeDosen extends AppCompatActivity {

    ArrayList<ClassMahasiswa> list_nilai;
    DBMahasiswa dbMahasiswa;
    ListView lv_dosen;
    ArrayAdapter<ClassMahasiswa>  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dosen);

        dbMahasiswa = Room.databaseBuilder(getApplicationContext(),DBMahasiswa.class,"DBNilai").build();
        list_nilai=new ArrayList<>();

        lv_dosen=findViewById(R.id.lv_dosen);

        getData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_dosen_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_dosen_insert:
                Intent i = new Intent(HomeDosen.this, DosenInsert.class);
                startActivity(i);
                break;
            default:
                finish();
                break;
        }
        return true;
    }

    private void getData(){
        new getNilai().execute();
        lv_dosen.setAdapter(adapter);
    }

    private class getNilai extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... Void) {
            list_nilai.addAll(dbMahasiswa.daoMahasiswa().getAllNilai());
            adapter = new ArrayAdapter<ClassMahasiswa>(getApplicationContext(),android.R.layout.simple_list_item_1, list_nilai);
            return null;
        }
    }
}
