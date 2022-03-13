package com.example.projektzaliczeniowyad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id_zamowienia, imie_zamowienia, email_zamowienia, telefon_zamowienia, data_zamowienia, pc_zamowienia, ilosc_zamowienia, myszka_zamowienia, klawa_zamowienia, kamera_zamowienia, cena_zamowienia;

    CustomAdapter(Context context, ArrayList id_zamowienia, ArrayList imie_zamowienia, ArrayList email_zamowienia, ArrayList telefon_zamowienia, ArrayList data_zamowienia, ArrayList pc_zamowienia, ArrayList ilosc_zamowienia,
                  ArrayList myszka_zamowienia, ArrayList klawa_zamowienia, ArrayList kamera_zamowienia, ArrayList cena_zamowienia){

        this.context = context;
        this.id_zamowienia = id_zamowienia;
        this.imie_zamowienia = imie_zamowienia;
        this.email_zamowienia = email_zamowienia;
        this.telefon_zamowienia = telefon_zamowienia;
        this.data_zamowienia = data_zamowienia;
        this.pc_zamowienia = pc_zamowienia;
        this.ilosc_zamowienia = ilosc_zamowienia;
        this.myszka_zamowienia = myszka_zamowienia;
        this.klawa_zamowienia = klawa_zamowienia;
        this.kamera_zamowienia = kamera_zamowienia;
        this.cena_zamowienia = cena_zamowienia;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id_txt.setText(String.valueOf(id_zamowienia.get(position)));
        holder.imie_txt.setText(String.valueOf(imie_zamowienia.get(position)));
        holder.email_txt.setText(String.valueOf(email_zamowienia.get(position)));
        holder.telefon_txt.setText(String.valueOf(telefon_zamowienia.get(position)));
        holder.data_txt.setText(String.valueOf(data_zamowienia.get(position)));
        holder.pc_txt.setText(String.valueOf(pc_zamowienia.get(position)));
        holder.ilosc_txt.setText(String.valueOf(ilosc_zamowienia.get(position)));
        holder.myszka_txt.setText(String.valueOf(myszka_zamowienia.get(position)));
        holder.klawa_txt.setText(String.valueOf(klawa_zamowienia.get(position)));
        holder.kamera_txt.setText(String.valueOf(kamera_zamowienia.get(position)));
        holder.cena_txt.setText(String.valueOf(cena_zamowienia.get(position)));
    }

    @Override
    public int getItemCount() {
        return id_zamowienia.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, imie_txt, email_txt, telefon_txt, data_txt, pc_txt, ilosc_txt, myszka_txt, klawa_txt, kamera_txt, cena_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id_txt = itemView.findViewById(R.id.id_zamowienia);
            imie_txt = itemView.findViewById(R.id.imie_zamowienia);
            email_txt = itemView.findViewById(R.id.email_zamowienia);
            telefon_txt = itemView.findViewById(R.id.telefon_zamowienia);
            data_txt = itemView.findViewById(R.id.data_zamowienia);
            pc_txt = itemView.findViewById(R.id.pc_zamowienia);
            ilosc_txt = itemView.findViewById(R.id.ilosc_zamowienia);
            myszka_txt = itemView.findViewById(R.id.myszka_zamowienia);
            klawa_txt = itemView.findViewById(R.id.klawiatura_zamowienia);
            kamera_txt = itemView.findViewById(R.id.kamera_zamowienia);
            cena_txt = itemView.findViewById(R.id.cena_zamowienia);
        }
    }
}
