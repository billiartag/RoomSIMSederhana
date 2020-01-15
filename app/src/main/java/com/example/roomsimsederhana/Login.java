package com.example.roomsimsederhana;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    EditText edNRP,edJurusan;
    Button btnLogin;
    DBMahasiswa dbMahasiswa;
    ArrayList<ClassMahasiswa> list_nilai;
    Boolean can_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbMahasiswa = Room.databaseBuilder(getApplicationContext(),DBMahasiswa.class,"DBNilai").build();
        list_nilai=new ArrayList<>();
        can_login=false;

        edNRP=findViewById(R.id.editTextLoginNRP);
        edJurusan=findViewById(R.id.editTextLoginJurusan);
        btnLogin=findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NRP = edNRP.getText().toString();
                String jurusan = edJurusan.getText().toString();
                cekLogin(NRP, jurusan);
            }
        });
    }

    private void cekLogin(String nrp, String jurusan) {
        if(nrp.equalsIgnoreCase("dosen")&&jurusan.equalsIgnoreCase("dosen")){
            Intent i = new Intent(Login.this, HomeDosen.class);
            startActivity(i);
        }
        else {
            mhs temp = new mhs(nrp, jurusan);
            new getNilai().execute(temp);
        }
    }

    private boolean cekDetail(String NRP, String jurusan){
        if(edJurusan.getText().toString().equalsIgnoreCase(jurusan)&&edNRP.getText().toString().equalsIgnoreCase(NRP)){
            return true;
        }
        else{
            return false;
        }
    }

    private class getNilai extends AsyncTask<mhs,Void,Void>{

        @Override
        protected Void doInBackground(mhs... mhs) {
            list_nilai.addAll(dbMahasiswa.daoMahasiswa().getAllNilai());

            //cek entry
            for (ClassMahasiswa r:list_nilai) {
                can_login= cekDetail(r.getNRP()+"", r.getJurusan());
                if(can_login){
                    Intent i = new Intent(Login.this, HomeSiswa.class);
                    i.putExtra("nrp",r.getNRP());
                    startActivity(i);
                    break;
                }
            }
            return null;
        }
    }
    class mhs{
        String nrp,jurusan;

        public mhs(String nrp, String jurusan) {
            this.nrp = nrp;
            this.jurusan = jurusan;
        }

        public String getNrp() {
            return nrp;
        }

        public void setNrp(String nrp) {
            this.nrp = nrp;
        }

        public String getJurusan() {
            return jurusan;
        }

        public void setJurusan(String jurusan) {
            this.jurusan = jurusan;
        }
    }
}
