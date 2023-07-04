package com.example.pemesanansalon.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pemesanansalon.R;
import com.example.pemesanansalon.model.Treatment;
import com.squareup.picasso.Picasso;

import java.util.List;
// sama kayak reservasi adapter
public class TreatmentAdapter extends RecyclerView.Adapter<TreatmentAdapter.TreatmentListViewHolder> {
    List<Treatment> treatmentList;

    public TreatmentAdapter(List<Treatment> treatmentList) {
        this.treatmentList = treatmentList;
    }

    @NonNull
    @Override
    public TreatmentAdapter.TreatmentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_treatment, parent, false);
        return new TreatmentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TreatmentAdapter.TreatmentListViewHolder holder, int position) {
        holder.bind(treatmentList.get(position));
    }

    @Override
    public int getItemCount() {
        return treatmentList.size();
    }

    public class TreatmentListViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewItem;
        TextView textViewItem;

        public TreatmentListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewItem = itemView.findViewById(R.id.imageViewItem);
            textViewItem = itemView.findViewById(R.id.textViewItem);
        }

        public void bind(Treatment theTreatment) {
            textViewItem.setText(theTreatment.getJudulTreatment());
            String imageUrl = theTreatment.getLinkgambarTreatment();
            if (!imageUrl.isEmpty()) {
                Picasso.get()
                        .load(imageUrl)
                        .into(imageViewItem);
            }

        }
    }
}
