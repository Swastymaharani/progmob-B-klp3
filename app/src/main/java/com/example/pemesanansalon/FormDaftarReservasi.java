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
        initialize();

        tanggalPesanTextInputLayout.setStartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //onclick pilih tanggal jika ikon kalender di klik
                final Calendar c = java.util.Calendar.getInstance();
                int year = c.get(java.util.Calendar.YEAR);
                int month = c.get(java.util.Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH)+1;

                DatePickerDialog datePicker = new DatePickerDialog(FormDaftarReservasi.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) { //set tanggal yang kepilih
                        tanggalPesanEditText.setText(String.format("%02d-%02d-%02d",dayOfMonth,month+1,year));
                    }
                },year,month,day);
                //set minimal book 1 hari setelah pesan
                datePicker.getDatePicker().setMinDate(c.getTimeInMillis());
                final Calendar c2 = java.util.Calendar.getInstance();
                //set maksimal book 2 bulan setelah pesan
                c2.set(year,month+2,day);
                datePicker.getDatePicker().setMaxDate(c2.getTimeInMillis());
                datePicker.show();
            }
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){ //onclick untuk order
                //falidasi tidak boleh kosong
                if(namaEditText.getText().toString().equals("")||
                        emailEditText.getText().toString().equals("")||
                        nomorTeleponEditText.getText().toString().equals("")||
                        tanggalPesanEditText.getText().toString().equals("")||
                        // falidasi jamnya harus diantara jam 8 sampe jam 16
                        !(Integer.parseInt(waktuPesanEditText.getText().toString())>=8&&Integer.parseInt(waktuPesanEditText.getText().toString())<=16)){
                    Snackbar.make(view,"Mohon periksa data formulir anda",Snackbar.LENGTH_SHORT)
                            .setAction("Tutup", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }).show();
                }else{
                    //buat reservasi baru dari data yg diinput
                    Reservasi reservasiBaru = new Reservasi(emailEditText.getText().toString(),namaEditText.getText().toString(),"+62"+nomorTeleponEditText.getText(),Integer.parseInt(waktuPesanEditText.getText().toString()),tanggalPesanEditText.getText().toString(),chosenTreatment.getJudulTreatment(),chosenTreatment.getDurasiJamTreatment());
                    reservasiList.add(reservasiBaru);
                    // masukin data ke database
                    mydatabase.execSQL("INSERT INTO tabelReservasi VALUES('"+
                            reservasiBaru.getEmail()+"','"+reservasiBaru.getNama()+"','"+
                            reservasiBaru.getTelepon()+"','"+reservasiBaru.getWaktuMulaiReservasi()+"','"+
                            reservasiBaru.getTanggalReservasi()+"','"+reservasiBaru.getTipeTreatment()+"','"+reservasiBaru.getDurasiTreatment()+"');");
                    // setelah data masuk kosongkan kembali semua edittext
                    namaEditText.setText("");
                    emailEditText.setText("");
                    nomorTeleponEditText.setText("");
                    tanggalPesanEditText.setText("");
                    waktuPesanEditText.setText("");

                    Snackbar.make(view,"Sukses Reservasi",Snackbar.LENGTH_SHORT)
                            .setAction("Tutup", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }).show();

                    Intent toReservasiSaya = new Intent(FormDaftarReservasi.this,ReservasiSaya.class);
                    toReservasiSaya.putExtra("data",chosenTreatment);
                    startActivity(toReservasiSaya);
                    finish();
                }
            }
        });
    }

    void initialize(){
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
        //membuat tabel reservasi
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS tabelReservasi(" +
                "email VARCHAR,nama VARCHAR,telepon VARCHAR, waktuReservasi VARCHAR,tanggalReservasi VARCHAR,tipeTreatment VARCHAR,durasiTreatment VARCHAR);");
        reservasiList = new ArrayList<>();
        chosenTreatment = (Treatment) getIntent().getSerializableExtra("data");
        Cursor resultSet = mydatabase.rawQuery("Select * from tabelReservasi",null);
        //masukan data satu persatu
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