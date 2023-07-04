package com.example.pemesanansalon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
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

        //fitur pilih treatment
        pilihTreatmentCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toPilihTreatMent = new Intent(MainActivity.this,PilihTreatment.class);
                startActivity(toPilihTreatMent);
            }
        });

        //fitur reservasimu
        reservasiCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toReservasiSaya = new Intent(MainActivity.this,ReservasiSaya.class);
                startActivity(toReservasiSaya);
            }
        });

        //fitur hubungi kami
        hubungiKamiCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = "Saya%20mau%20bertanya%20/%20konfirmasi%20tentang%20Pemesanan%20Salon!";
                String url = String.format("https://wa.me/+6283115480979?text=%s",text);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        //fitur lokasi kami
        lokasiKamiCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("geo:0,0?q=-8.795945132892731, 115.17621799491707");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
    void initialize(){
        pilihTreatmentCardView = findViewById(R.id.pilihTreatmentCardView);
        reservasiCardView = findViewById(R.id.reservasiCardView);
        hubungiKamiCardView = findViewById(R.id.hubungiKamiCardView);
        lokasiKamiCardView = findViewById(R.id.lokasiKamiCardView);

    }
}