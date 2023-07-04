package com.example.pemesanansalon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pemesanansalon.model.Treatment;

public class DetailTreatment extends AppCompatActivity {
    Treatment chosenTreatment;
    TextView titleTextView;
    TextView typeTextView;
    TextView durationTextView;
    TextView descriptionTextView;
    Button bookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_treatment);
        initialize();
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {// onclick menuju form
                Intent toReservationForm = new Intent(DetailTreatment.this,FormDaftarReservasi.class);
                toReservationForm.putExtra("data",chosenTreatment);
                startActivity(toReservationForm);
            }
        });
    }

    void initialize(){
        titleTextView = findViewById(R.id.treatmentDetailsTitleTextView);
        typeTextView = findViewById(R.id.treatmentDetailsTypeTextView);
        durationTextView = findViewById(R.id.treatmentDetailsDurationTextView);
        descriptionTextView = findViewById(R.id.treatmentDetailsDescriptionTextView);
        bookButton = findViewById(R.id.bookButton);

        //ambil data dari pilih treatmen tadi untuk ditampilkan
        chosenTreatment = (Treatment) getIntent().getSerializableExtra("data");
        titleTextView.setText(String.format("Treatment : %s",chosenTreatment.getJudulTreatment()));
        typeTextView.setText(String.format("Jenis Treatment : %s",chosenTreatment.getJenisTreatment()));
        durationTextView.setText(String.format("Durasi : %d jam",chosenTreatment.getDurasiJamTreatment()));
        descriptionTextView.setText(String.format("Deskripsi : %s",chosenTreatment.getDeskripsiTreatment()));
    }
}