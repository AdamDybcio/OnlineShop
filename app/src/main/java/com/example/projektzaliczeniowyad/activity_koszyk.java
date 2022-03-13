package com.example.projektzaliczeniowyad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class activity_koszyk extends AppCompatActivity {

    RecyclerView recyclerView;

    MyDatabaseHelper myDB;
    ArrayList id, imie, email, data, telefon, pc, ilosc, myszka, klawa, kamera, cena;
    TextView brak_danych;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koszyk);


        myDB = new MyDatabaseHelper(activity_koszyk.this);
        id = new ArrayList<>();
        imie = new ArrayList<>();
        email = new ArrayList<>();
        telefon = new ArrayList<>();
        data = new ArrayList<>();
        pc = new ArrayList<>();
        ilosc = new ArrayList<>();
        myszka = new ArrayList<>();
        klawa = new ArrayList<>();
        kamera = new ArrayList<>();
        cena = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        brak_danych = findViewById(R.id.brak_danych);

        doArraya();

        customAdapter = new CustomAdapter(activity_koszyk.this, id, imie, email, telefon, data, pc, ilosc, myszka, klawa, kamera, cena);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity_koszyk.this));

    }

    void doArraya(){
        Cursor cursor = myDB.czytajDane();
        if (cursor.getCount() == 0){
            brak_danych.setVisibility(View.VISIBLE);
        } else {
            brak_danych.setVisibility(View.INVISIBLE);
            while (cursor.moveToNext()) {
                id.add(cursor.getString(0));
                imie.add(cursor.getString(1));
                email.add(cursor.getString(2));
                telefon.add(cursor.getString(3));
                data.add(cursor.getString(4));
                pc.add(cursor.getString(5));
                ilosc.add(cursor.getString(6));
                myszka.add(cursor.getString(7));
                klawa.add(cursor.getString(8));
                kamera.add(cursor.getString(9));
                cena.add(cursor.getString(10));;
            }
        }
    }

}