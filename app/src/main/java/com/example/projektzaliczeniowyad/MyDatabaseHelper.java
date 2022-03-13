package com.example.projektzaliczeniowyad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Zamowienia.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "wszystkie_zamowienia";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "imie_nazwisko";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "telefon";
    private static final String COLUMN_DATE = "data";
    private static final String COLUMN_PC = "pc";
    private static final String COLUMN_ITEM_COUNT = "ilosc";
    public static final String COLUMN_MOUSE = "myszka";
    public static final String COLUMN_KEYBOARD = "klawiatura";
    public static final String COLUMN_CAMERA = "kamera";
    private static final String COLUMN_PRICE = "cena";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_EMAIL + " TEXT, " +
                        COLUMN_PHONE + " TEXT, " +
                        COLUMN_DATE + " DATE, " +
                        COLUMN_PC + " TEXT, " +
                        COLUMN_ITEM_COUNT + " INTEGER, " +
                        COLUMN_MOUSE + " TEXT, " +
                        COLUMN_KEYBOARD + " TEXT, " +
                        COLUMN_CAMERA + " TEXT, " +
                        COLUMN_PRICE + " INTEGER);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    String blad;

    void dodajZamowienie(String imie, String email, String telefon, Date data, String pc, int ilosc, String myszka, String klawa, String kamera, int cena){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if(imie == null || imie.equals("") || email == null || email.equals("") || telefon == null || telefon.equals("") || telefon.length() != 9){
            blad = context.getString(R.string.podaj_dane);
        }else {
            cv.put(COLUMN_NAME, imie);
            cv.put(COLUMN_EMAIL, email);
            cv.put(COLUMN_PHONE, telefon);
            cv.put(COLUMN_DATE, String.valueOf(data));
            cv.put(COLUMN_PC, pc);
            cv.put(COLUMN_ITEM_COUNT, ilosc);
            cv.put(COLUMN_MOUSE, myszka);
            cv.put(COLUMN_KEYBOARD, klawa);
            cv.put(COLUMN_CAMERA, kamera);
            cv.put(COLUMN_PRICE, cena);

            blad = context.getString(R.string.blad);
        }
        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1){
            Toast.makeText(context, blad, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.zamowiono_toast , Toast.LENGTH_LONG).show();
        }

        czytajDane();
    }

    Cursor czytajDane() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }
}
