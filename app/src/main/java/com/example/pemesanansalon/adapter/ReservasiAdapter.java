package com.example.pemesanansalon.adapter;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pemesanansalon.R;
import com.example.pemesanansalon.model.Reservasi;

import java.util.List;
// adapter buat reservasi, harusnya kelen udh bljr ini, kalau belum mana bs buat recycler view
// harusnya udh tau cara buat
public class ReservasiAdapter extends RecyclerView.Adapter<ReservasiAdapter.ReservasiListViewHolder>{
    public static final int MODE_PRIVATE = 0x0000;
    List<Reservasi> reservasiList;
    public ReservasiAdapter(List<Reservasi> reservasiList) {
        this.reservasiList = reservasiList;
    }
    @NonNull
    @Override
    public ReservasiAdapter.ReservasiListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservasi, parent, false);
        return new  ReservasiAdapter.ReservasiListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservasiAdapter.ReservasiListViewHolder holder, int position) {
        holder.bind(reservasiList.get(position));
    }

    @Override
    public int getItemCount() {
        return  reservasiList.size();
    }
    // semua UI dari itemnya set disini
    public class ReservasiListViewHolder extends RecyclerView.ViewHolder {
        TextView namaTextView;
        TextView emailTextView;
        TextView teleponTextView;
        TextView waktuReservasiTextView;
        TextView tanggalReservasiTextView;
        TextView tipeTreatmentTextView;

        public ReservasiListViewHolder(@NonNull View itemView) {
            super(itemView);
            // ini sama kek initialize()
            namaTextView = itemView.findViewById(R.id.namaTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            teleponTextView = itemView.findViewById(R.id.teleponTextView);
            waktuReservasiTextView = itemView.findViewById(R.id.waktuReservasiTextView);
            tanggalReservasiTextView = itemView.findViewById(R.id.tanggalReservasiTextView);
            tipeTreatmentTextView = itemView.findViewById(R.id.tipeTreatmentTextView);
        }

        public void bind(Reservasi reservasi) {
            // masukin data ke UInya
            namaTextView.setText(reservasi.getNama());
            emailTextView.setText(reservasi.getEmail());
            teleponTextView.setText("Telepon :"+reservasi.getTelepon());

            waktuReservasiTextView.setText(
                    String.format("Waktu Reservasi : %d:00 - %d:00",
                            reservasi.getWaktuMulaiReservasi(),reservasi.getWaktuMulaiReservasi()+reservasi.getDurasiTreatment()));
            tanggalReservasiTextView.setText(reservasi.getTanggalReservasi());
            tipeTreatmentTextView.setText(reservasi.getTipeTreatment());
        }
    }
}
