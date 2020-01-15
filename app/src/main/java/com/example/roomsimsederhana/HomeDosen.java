package com.example.roomsimsederhana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
        lv_dosen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent j = new Intent(HomeDosen.this, DosenInsert.class);
                j.putExtra("data", list_nilai.get(i));
                j.putExtra("jenis","update");
                startActivity(j);
            }
        });

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
                i.putExtra("jenis","insert");
                startActivity(i);
                break;
            default:
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData(){
        new getNilai().execute();
    }

    private void setLV(){
        adapter = new ArrayAdapter<ClassMahasiswa>(this,android.R.layout.simple_list_item_1, list_nilai);
        lv_dosen.setAdapter(adapter);
        Log.d("sz", list_nilai.size()+"");
    }

    private class getNilai extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... Void) {
            list_nilai.clear();
            list_nilai.addAll(dbMahasiswa.daoMahasiswa().getAllNilai());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setLV();
        }
    }
}
