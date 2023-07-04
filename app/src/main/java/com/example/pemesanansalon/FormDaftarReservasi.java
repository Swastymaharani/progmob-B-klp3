package com.example.pemesanansalon;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.pemesanansalon.model.Reservasi;
import com.example.pemesanansalon.model.Treatment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FormDaftarReservasi extends AppCompatActivity {
    // 5. Form daftar reservasi
    Treatment chosenTreatment;
    EditText namaEditText;
    EditText emailEditText;
    TextInputLayout nomorTeleponTextInputLayout;
    EditText nomorTeleponEditText;
    TextInputLayout tanggalPesanTextInputLayout;
    EditText tanggalPesanEditText;
    TextInputLayout waktuPesanTextInputLayout;
    EditText waktuPesanEditText;
    Button orderBtn;
    List<Reservasi> reservasiList;
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_daftar_reservasi);
        initialize();//initilaize cek function
        // ini yg tanggal kalauicon kalender diclick
        tanggalPesanTextInputLayout.setStartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kalo icon kalender diclick kita buat kalender dulu ambil, tanggal hari ini
                final Calendar c = java.util.Calendar.getInstance();
                int year = c.get(java.util.Calendar.YEAR);
                int month = c.get(java.util.Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH)+1;
                // ini UI pilih tanggalnya
                DatePickerDialog datePicker = new DatePickerDialog(FormDaftarReservasi.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //kalau tanggal udh dipilih kita isi ke edit text tanggal pesannya
                        tanggalPesanEditText.setText(String.format("%02d-%02d-%02d",dayOfMonth,month+1,year));
                    }
                },year,month,day);
                //set min data adalah minimal tanggal yaitu kita set jadi minimal besok baru bs pesan
                datePicker.getDatePicker().setMinDate(c.getTimeInMillis());
                final Calendar c2 = java.util.Calendar.getInstance();
                c2.set(year,month+2,day);
                //bikin kalender 2 bulan dari hari ini, habis itu kita set maksimal tanggal yg bs dipilih itu 2 bulan dari hari ini
                datePicker.getDatePicker().setMaxDate(c2.getTimeInMillis());
                datePicker.show();// show kalender
            }
        });
        //kalau order tombol ditekan
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // pas ditekan kita cek dulu kalau kolom"nya semua valid yaitu kolom tdk bole kosong, dan
                if(namaEditText.getText().toString().equals("")||
                        emailEditText.getText().toString().equals("")||
                        nomorTeleponEditText.getText().toString().equals("")||
                        tanggalPesanEditText.getText().toString().equals("")||
                        // jamnya harus diantara jam 8 sampe jam 16
                        !(Integer.parseInt(waktuPesanEditText.getText().toString())>=8&&Integer.parseInt(waktuPesanEditText.getText().toString())<=16)){
                    // kalau tidak memenuhi bakala muncul snackbar bilang mohom periksa data formulir anda
                    Snackbar.make(view,"Mohon periksa data formulir anda",Snackbar.LENGTH_SHORT)
                            .setAction("Tutup", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // jangan sentuh kalau gk ngerti
                                    // default kosong
                                    // snackbar ada tombol tutup ini kosong tapi pas ditekan kalau mau apa" taro disini
                                }
                            }).show();
                }else{
                    //bikin reservasi baru dari data yg diinput
                    Reservasi reservasiBaru = new Reservasi(emailEditText.getText().toString(),namaEditText.getText().toString(),"+62"+nomorTeleponEditText.getText(),Integer.parseInt(waktuPesanEditText.getText().toString()),tanggalPesanEditText.getText().toString(),chosenTreatment.getJudulTreatment(),chosenTreatment.getDurasiJamTreatment());
                    // masukin ke list reservasi
                    reservasiList.add(reservasiBaru);
                    // masukin ke database query
                    mydatabase.execSQL("INSERT INTO tabelReservasi VALUES('"+
                            reservasiBaru.getEmail()+"','"+reservasiBaru.getNama()+"','"+
                            reservasiBaru.getTelepon()+"','"+reservasiBaru.getWaktuMulaiReservasi()+"','"+
                            reservasiBaru.getTanggalReservasi()+"','"+reservasiBaru.getTipeTreatment()+"','"+reservasiBaru.getDurasiTreatment()+"');");
                    // kosongin balik semua edittext
                    namaEditText.setText("");
                    emailEditText.setText("");
                    nomorTeleponEditText.setText("");
                    tanggalPesanEditText.setText("");
                    waktuPesanEditText.setText("");
                    // munculin bahwa reservasi sukses
                    Snackbar.make(view,"Sukses Reservasi",Snackbar.LENGTH_SHORT)
                            .setAction("Tutup", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }).show();
                    // balik ke main activity yaitu main menu
                    Intent toReservasiSaya = new Intent(FormDaftarReservasi.this,ReservasiSaya.class);
                    toReservasiSaya.putExtra("data",chosenTreatment);
                    startActivity(toReservasiSaya);
                    finish();// biar org gbs balik ke sini
                }
            }
        });
    }

    void initialize(){// view taro ke variabel semua
        namaEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        nomorTeleponTextInputLayout = findViewById(R.id.phoneTextInputLayout);
        nomorTeleponEditText = findViewById(R.id.phoneEditText);
        tanggalPesanTextInputLayout = findViewById(R.id.reserveDateTextInputLayout);
        tanggalPesanEditText= findViewById(R.id.reserveDateEditText);
        waktuPesanTextInputLayout = findViewById(R.id.reserveTimeTextInputLayout);
        waktuPesanEditText= findViewById(R.id.reserveTimeEditText);
        orderBtn = findViewById(R.id.orderBtn);
        //database sqllite
        mydatabase = openOrCreateDatabase("reservasi",MODE_PRIVATE,null);
        //kalau tablenya gak ada kita buat
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS tabelReservasi(" +
                "email VARCHAR,nama VARCHAR,telepon VARCHAR, waktuReservasi VARCHAR,tanggalReservasi VARCHAR,tipeTreatment VARCHAR,durasiTreatment VARCHAR);");
        //reservasinya kita taro jadi list kosong
        reservasiList = new ArrayList<>();
        //ambil data treatment terpilih yg dipassing
        chosenTreatment = (Treatment) getIntent().getSerializableExtra("data");
        //kita query data
        Cursor resultSet = mydatabase.rawQuery("Select * from tabelReservasi",null);
        //1 per satu table yg kita dapat hasilnya kita jadiin variabel reservasi
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