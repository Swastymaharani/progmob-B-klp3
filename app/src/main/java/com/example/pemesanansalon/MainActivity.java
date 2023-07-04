package com.example.pemesanansalon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    // 2. Main activity yaitu main menu
    //Ada 4 card view untuk 4 menu/fitur
    CardView pilihTreatmentCardView;
    CardView reservasiCardView;
    CardView hubungiKamiCardView;
    CardView lokasiKamiCardView;

    CardView LoginAkun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();// cek function initialize

        pilihTreatmentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // intent ke pilih treatment menu 1
                Intent toPilihTreatMent = new Intent(MainActivity.this,PilihTreatment.class);
                startActivity(toPilihTreatMent);
            }
        });
        reservasiCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intent ke reservasi saya menu 2
                Intent toReservasiSaya = new Intent(MainActivity.this,ReservasiSaya.class);
                startActivity(toReservasiSaya);
            }
        });
        hubungiKamiCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // intent ke whatsapp menu 4
                // text ini adalah pesan ke whatsapp pas mereka masuk ke whatsapp
                String text = "Saya%20mau%20bertanya%20/%20konfirmasi%20tentang%20Pemesanan%20Salon!";
                // ini link ke whatsapp lu
                String url = String.format("https://wa.me/+6283115480979?text=%s",text);
                //intent link
                Intent i = new Intent(Intent.ACTION_VIEW);
                // taro datanya alias linknya ke intent link
                i.setData(Uri.parse(url));
                startActivity(i);// intent
            }
        });
        lokasiKamiCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("geo:0,0?q=-8.795945132892731, 115.17621799491707");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
    void initialize(){// semua yg di initialize itu untuk masukin variabel cari UInya
        pilihTreatmentCardView = findViewById(R.id.pilihTreatmentCardView);
        reservasiCardView = findViewById(R.id.reservasiCardView);
        hubungiKamiCardView = findViewById(R.id.hubungiKamiCardView);
        lokasiKamiCardView = findViewById(R.id.lokasiKamiCardView);

    }
}