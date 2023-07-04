package com.example.pemesanansalon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pemesanansalon.adapter.TreatmentAdapter;
import com.example.pemesanansalon.listener.RecyclerItemClickListener;
import com.example.pemesanansalon.model.Treatment;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PilihTreatment extends AppCompatActivity {
    //pilih treatment ada recycler view
    RecyclerView treatmentListRecycler;
    List<Treatment> treatmentListData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_treatment);
        initialize();
    }

    void initialize(){
        treatmentListRecycler = findViewById(R.id.recyclerTreatmentList);
        treatmentListData = new ArrayList<>();
        generateTreatment();// generate tratment untuk generasi treatment
        treatmentListRecycler.setAdapter(new TreatmentAdapter(treatmentListData)); //adapter recyclerview
        treatmentListRecycler.addOnItemTouchListener(new RecyclerItemClickListener(treatmentListRecycler, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View inView, int inPosition) throws IOException { //onclick ke detail treatment
                Intent toDetailTreatment = new Intent(PilihTreatment.this,DetailTreatment.class);
                toDetailTreatment.putExtra("data",treatmentListData.get(inPosition));
                startActivity(toDetailTreatment);//intent
            }
        }));
    }
    void generateTreatment(){ //data treatment untuk dipasing ke detail treatment
        treatmentListData.add(new Treatment("0","Hair Cut",
                "https://hollandaputri.files.wordpress.com/2023/06/2.png?w=500",
                "Layanan potong rambut yang dilakukan oleh para ahli tata rambut di True Beauty Salon dengan teknik pemotongan yang presisi dan gaya potongan yang sesuai dengan kepribadian dan sesuai dengan keinginan Anda",
                1,"Rambut"));// tambah data
        treatmentListData.add(new Treatment("1","Hair Coloring",
                "https://hollandaputri.files.wordpress.com/2023/06/3.png?w=500",
                " Layanan pewarnaan rambut yang menggunakan produk pewarna berkualitas tinggi, teknik pewarnaan terbaru dan Anda bebas request warna ",
                4,"Rambut"));
        treatmentListData.add(new Treatment("2","Face SPA",
                "https://hollandaputri.files.wordpress.com/2023/06/desain-tanpa-judul-3.png?w=500",
                "Perawatan wajah yang akan membersihkan, menghidrasi, dan memberikan nutrisi pada kulit wajah Anda, sehingga kulit terasa segar, bercahaya, dan terawat dengan baik.",
                1,"Wajah"));
        treatmentListData.add(new Treatment("3","Face Masker",
                "https://hollandaputri.files.wordpress.com/2023/06/5.png?w=500",
                "Perawatan wajah dengan pengaplikasian masker wajah yang dapat menutrisi kulit wajah. Menggunakan masker yang sesuai dengan kebutuhan kulit wajah Anda.",
                1,"Wajah"));
        treatmentListData.add(new Treatment("4","Body SPA",
                "https://hollandaputri.files.wordpress.com/2023/06/6.png?w=500",
                "Perawatan tubuh yang menggunakan kombinasi pijatan yang menenangkan, scrub tubuh yang lembut, dan perawatan kulit tubuh yang menyegarkan. Membantu meremajakan tubuh Anda, mengurangi stres, dan meningkatkan kesejahteraan secara keseluruhan.  ",
                1,"Badan"));
        treatmentListData.add(new Treatment("5","Nail Art",
                "https://hollandaputri.files.wordpress.com/2023/06/4.png?w=500",
                "Layanan menghias kuku dengan menggunakan beragam teknik dan aksesori dekoratif yang akan memberikan sentuhan estetika pada kuku Anda, menjadikannya cantik dan menawan.",
                1,"Kuku"));
        treatmentListData.add(new Treatment("6","Make Up",
                "https://hollandaputri.files.wordpress.com/2023/06/desain-tanpa-judul-2.png?w=500",
                "Layanan tata rias wajah dengan berbagai jenis make up sesuai kebutuhan yang Anda inginkan.",
                1,"Wajah"));
    }
}