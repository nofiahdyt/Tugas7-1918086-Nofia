package com.example.tugaspraktikum7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainRead extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView mListView;
    private CustomListAdapter adapter_off;
    private MyDatabase db;
    private List<Dokter> ListDokter = new ArrayList<Dokter>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_read);
        db = new MyDatabase(this);
        adapter_off = new CustomListAdapter(this, ListDokter
        );
        mListView = (ListView) findViewById(R.id.list_dokter);
        mListView.setAdapter(adapter_off);
        mListView.setOnItemClickListener(this);
        mListView.setClickable(true);
        ListDokter.clear();
        List<Dokter> dokter = db.ReadDokter();
        for (Dokter mhs : dokter) {
            Dokter daftar = new Dokter();
            daftar.set_id(mhs.get_id());
            daftar.set_nama(mhs.get_nama());
            daftar.set_sp(mhs.get_sp());
            ListDokter.add(daftar);
            if ((ListDokter.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
        Object o = mListView.getItemAtPosition(i);
        Dokter detailMhs = (Dokter) o;
        String Sid = detailMhs.get_id();
        String Snama = detailMhs.get_nama();
        String Ssp = detailMhs.get_sp();
        Intent goUpdel = new Intent(MainRead.this, MainUpdel.class);
        goUpdel.putExtra("Iid", Sid);
        goUpdel.putExtra("Inama", Snama);
        goUpdel.putExtra("Isp", Ssp);
        startActivity(goUpdel);
    }
    @Override
    protected void onResume() {
        super.onResume();
        ListDokter.clear();
        mListView.setAdapter(adapter_off);
        List<Dokter> dokter = db.ReadDokter();
        for (Dokter mhs : dokter) {
            Dokter daftar = new Dokter();
            daftar.set_id(mhs.get_id());
            daftar.set_nama(mhs.get_nama());
            daftar.set_sp(mhs.get_sp());
            ListDokter.add(daftar);
            if ((ListDokter.isEmpty()))
                Toast.makeText(MainRead.this, "Tidak ada data",
                        Toast.LENGTH_SHORT).show();
            else {
            }
        }
    }
}
