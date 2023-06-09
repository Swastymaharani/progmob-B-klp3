package com.example.pemesanansalon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.pemesanansalon.adapter.ReservasiAdapter;
import com.example.pemesanansalon.adapter.TreatmentAdapter;
import com.example.pemesanansalon.model.Reservasi;

import java.util.ArrayList;
import java.util.List;

public class ReservasiSaya extends AppCompatActivity {
    SQLiteDatabase mydatabase;
    RecyclerView reservasiListRecyclerView;
    List<Reservasi> reservasiList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasi_saya);
        initialize();
    }

    void initialize(){
        reservasiListRecyclerView = findViewById(R.id.recyclerReservasiList);
        loadDatabase();// load database untuk ambil datanya
        reservasiListRecyclerView.setAdapter(new ReservasiAdapter(reservasiList));// set reservasi adapter
    }

    void loadDatabase(){
        // konek database
        mydatabase = openOrCreateDatabase("reservasi",MODE_PRIVATE,null);
        //buat tabelnnya jika belum ada
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS tabelReservasi(" +
                "email VARCHAR,nama VARCHAR,telepon VARCHAR, waktuReservasi VARCHAR,tanggalReservasi VARCHAR,tipeTreatment VARCHAR);");
        reservasiList = new ArrayList<>();
        Cursor resultSet = mydatabase.rawQuery("Select * from tabelReservasi",null);
        for(int i=0;i<resultSet.getCount();i++){
            resultSet.moveToPosition(i);
            String email = resultSet.getString(0);
            String nama = resultSet.getString(1);
            String telepon = resultSet.getString(2);
            int waktuReservasi = Integer.parseInt(resultSet.getString(3));
            String tanggalReservasi = resultSet.getString(4);
            String tipeTreatment = resultSet.getString(5);
            int durasiTreatment = Integer.parseInt(resultSet.getString(6));

            //datanya diambil semua masukin jadi reservasi
            Reservasi reservasiBaru = new Reservasi(email,nama,telepon,waktuReservasi,tanggalReservasi,tipeTreatment,durasiTreatment);
            //masukin ke daftar reservasi
            reservasiList.add(reservasiBaru);
        }
    }
}