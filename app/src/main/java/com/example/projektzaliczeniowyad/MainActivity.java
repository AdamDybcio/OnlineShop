package com.example.projektzaliczeniowyad;

import static java.lang.String.*;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;

import javax.mail.Quota;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String [] opisy_kompy = {"Intel Core i5 12400 6 X 2,4 GHz 16GB DDR4 SSD 250GB M.2 + HDD 1TB Sata GTX 1050 Ti 4GB \n3824PLN" /*+ getString(R.string.cena_pc1)*/,
            "Intel Core i7 11700 8 X 2,9 GHz 16GB DDR4 SSD 500GB M.2 + GTX 1660 6GB \n5832PLN" /*+ getString(R.string.cena_pc2)*/,
            "RYZEN 9 3900X 12 x 3,8GHz 32GB DDR4 SSD 500GB M.2 GTX 2060 6GB \n7764PLN" /*+ getString(R.string.cena_pc3)*/
    };

    Integer [] ceny_kompy = {3824, 5832, 7764};

    String [] opisy_myszki = {"Razer Basilisk Essential \n89PLN" /*+ getString(R.string.cena_myszka1)*/,
            "Logitech G502 Hero High Performance \n269PLN" /*+ getString(R.string.cena_myszka2)*/,
            "SteelSeries Aerox 3 \n199PLN" /*+ getString(R.string.cena_myszka3)*/
    };

    Integer [] ceny_myszki = {89, 269, 199};

    String [] opisy_klawy = {"HyperX Alloy Origins Blue Switch \n329PLN" /*+ getString(R.string.cena_klawa1)*/,
            "Corsair K68 Red Led Cherry MX Red \n349PLN" /*+ getString(R.string.cena_klawa2)*/,
            "Logitech G413 TKL SE \n299PLN" /*+ getString(R.string.cena_klawa3)*/
    };

    Integer [] ceny_klawy = {329, 349, 299};

    String [] opisy_kamery = {"Logitech HD Webcam C270 \n129PLN" /*+ getString(R.string.cena_kamera1)*/,
            "Tracer WEB007 \n119PLN" /*+ getString(R.string.cena_kamera2)*/,
            "Tracer WEB008 \n79PLN" /*+ getString(R.string.cena_kamera3)*/
    };

    Integer [] ceny_kamery = {129, 119, 79};

    Spinner spinner1, spinner2, spinner3, spinner4;
    Button koszyk;
    Button dodaj;
    EditText imie0;
    EditText email0;
    EditText telefon0;
    Locale language = new Locale("pl");
    String telefon;
    String imie, email;
    Button jezyk, polski, angielski;
    String pc, myszka, klawa, kamera;
    int ilosc = 1;
    int cena1, cena2, cena3, cena4 = 0;
    Slider slider;
    Date data;
    TextView start_cena;
    int koszt = ceny_kompy[0];
    int ktory_pc = 0;
    int ktora_myszka = 0;
    int ktora_klawa = 0;
    int ktora_kamera = 0;

    String numerTel = "";

    TextView dane_napis;

    CheckBox ch_myszka, ch_klawa, ch_kamera;

    //SwitchCompat switchCompat;

    private final Slider.OnSliderTouchListener touchListener =
            new Slider.OnSliderTouchListener() {
                @Override
                public void onStartTrackingTouch(Slider slider) {

                }

                @Override
                public void onStopTrackingTouch(Slider slider) {
                    ilosc = Math.round(slider.getValue());

                    koszt = cena1 * ilosc + cena2 + cena3 + cena4 ;

                    start_cena.setText("" + koszt);
                }
            };

    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setAppLocale("en");

        setContentView(R.layout.activity_main);

        //switchCompat.findViewById(R.id.switchCompat);

        ch_myszka = findViewById(R.id.myszka_checkbox);
        ch_klawa = findViewById(R.id.klawiatura_checkbox);
        ch_kamera = findViewById(R.id.kamera_checkbox);

        dane_napis = findViewById(R.id.dane_napis);


        ch_klawa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                } else{
                }
            }
        });

        ch_kamera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                } else{
                }
            }
        });

//        koszyk = (Button) findViewById(R.id.koszyk);
        dodaj = (Button) findViewById(R.id.dodaj);
        data = Calendar.getInstance().getTime();
        imie0 = findViewById(R.id.dane);
        telefon0 = findViewById(R.id.telefon);
        email0 = findViewById(R.id.email);
        start_cena = findViewById(R.id.cena);
        pc = opisy_kompy[ktory_pc];
        myszka = opisy_myszki[ktora_myszka];
        klawa = opisy_klawy[ktora_klawa];
        kamera = opisy_kamery[ktora_kamera];

        cena1 = ceny_kompy[0];

        Locale.setDefault(language);

        start_cena.setText("" + cena1);

//        koszyk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, activity_koszyk.class);
//                startActivity(intent);
//            }
//        });
        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imie = imie0.getText().toString().trim();
                email = email0.getText().toString().trim();
                telefon = telefon0.getText().toString().trim();
                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.dodajZamowienie(imie, email, telefon, data, pc.trim() , ilosc, myszka.trim(), klawa.trim(), kamera.trim(), koszt);
                if (imie !=null && email != null && !imie.equals("") && !email.equals("") && telefon != null && !telefon.equals("") && telefon.length() == 9){
                    sendEmail();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                        if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                            sendSms();

                        } else {
                            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                        }

                    }

                    imie0.getText().clear();
                    email0.getText().clear();
                    telefon0.getText().clear();
                    spinner1.setSelection(0);
                    spinner2.setSelection(0);
                    spinner3.setSelection(0);
                    spinner4.setSelection(0);
                    slider.setValue(1);
                    ch_kamera.setChecked(true);
                    ch_myszka.setChecked(true);
                    ch_klawa.setChecked(true);
                }

            }
        });

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);

        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(this);

        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner4.setOnItemSelectedListener(this);


        ArrayAdapter arrayAdapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opisy_kompy);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);

        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opisy_myszki);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);

        ArrayAdapter arrayAdapter3 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opisy_klawy);
        arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(arrayAdapter3);

        ArrayAdapter arrayAdapter4 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opisy_kamery);
        arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(arrayAdapter4);

        int[] pcty = {
                R.drawable.komp1,
                R.drawable.komp2,
                R.drawable.komp3
        };

        int[] myszki = {
                R.drawable.mysz1,
                R.drawable.mysz2,
                R.drawable.mysz3
        };

        int[] klawy = {
                R.drawable.klawa1,
                R.drawable.klawa2,
                R.drawable.klawa3
        };

        int[] kamery = {
                R.drawable.kamera1,
                R.drawable.kamera2,
                R.drawable.kamera3
        };

        MyAdapter myAdapter1 = new MyAdapter(getApplicationContext(), pcty, opisy_kompy);
        spinner1.setAdapter(myAdapter1);

        MyAdapter myAdapter2 = new MyAdapter(getApplicationContext(), myszki, opisy_myszki);
        spinner2.setAdapter(myAdapter2);

        MyAdapter myAdapter3 = new MyAdapter(getApplicationContext(), klawy, opisy_klawy);
        spinner3.setAdapter(myAdapter3);

        MyAdapter myAdapter4 = new MyAdapter(getApplicationContext(), kamery, opisy_kamery);
        spinner4.setAdapter(myAdapter4);

        slider = findViewById(R.id.ilosc_slider);
        slider.addOnSliderTouchListener(touchListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.historia_zamowien:
                    Intent intent = new Intent(MainActivity.this, activity_koszyk.class);
                    startActivity(intent);
                return true;
            case R.id.autor:
                    Intent intent2 = new Intent(MainActivity.this, activity_autor.class);
                    startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView == spinner1) {
            spinner1.getItemAtPosition(i);
            ktory_pc = i;
            pc = opisy_kompy[ktory_pc];
            cena1 = ceny_kompy[i];
        } else if(adapterView == spinner2 && ch_myszka.isChecked()){
            ch_myszka.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        spinner2.getItemAtPosition(i);
                        myszka = opisy_myszki[i];
                        cena2 = ceny_myszki[i];
                        koszt = cena1 * ilosc + cena2 + cena3 + cena4 ;

                        start_cena.setText("" + koszt);
                    } else{
                        myszka =getString(R.string.brak);
                        cena2 = 0;
                        koszt = cena1 * ilosc + cena2 + cena3 + cena4 ;

                        start_cena.setText("" + koszt);
                    }
                }
            });
            myszka = opisy_myszki[i];
            spinner2.getItemAtPosition(i);
            cena2 = ceny_myszki[i];
        } else if(adapterView == spinner3 && ch_klawa.isChecked()){
            ch_klawa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        spinner3.getItemAtPosition(i);
                        klawa = opisy_klawy[i];
                        cena3 = ceny_klawy[i];
                        koszt = cena1 * ilosc + cena2 + cena3 + cena4 ;

                        start_cena.setText("" + koszt);
                    } else{
                        klawa = getString(R.string.brak);
                        cena3 = 0;
                        koszt = cena1 * ilosc + cena2 + cena3 + cena4 ;

                        start_cena.setText("" + koszt);
                    }
                }
            });
            klawa = opisy_klawy[i];
            spinner3.getItemAtPosition(i);
            cena3 = ceny_klawy[i];
        } else if(adapterView == spinner4 && ch_kamera.isChecked()){
            ch_kamera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b){
                        spinner4.getItemAtPosition(i);
                        kamera = opisy_kamery[i];
                        cena4 = ceny_kamery[i];
                        koszt = cena1 * ilosc + cena2 + cena3 + cena4 ;

                        start_cena.setText("" + koszt);
                    } else{
                        kamera = getString(R.string.brak);
                        cena4 = 0;
                        koszt = cena1 * ilosc + cena2 + cena3 + cena4 ;

                        start_cena.setText("" + koszt);
                    }
                }
            });
            kamera = opisy_kamery[i];
            spinner4.getItemAtPosition(i);
            cena4 = ceny_kamery[i];
        }

        koszt = cena1 * ilosc + cena2 + cena3 + cena4 ;

        start_cena.setText("" + koszt);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        start_cena.setText("" + cena1);
    }

    protected void sendEmail() {

        String mEmail = email0.getText().toString();
        String mSubject = getString(R.string.temat_email);
        String mMessage = getString(R.string.wiadomosc_email1) + imie + getString(R.string.wiadomosc_email2) + "\n\n\n" + getString(R.string.wiadomosc_email10) + "\n\n" + getString(R.string.wiadomosc_email3)  + pc + "\n\n" + getString(R.string.wiadomosc_email4) + ilosc + "\n\n\n" + getString(R.string.wiadomosc_email5)
                + "\n\n" + getString(R.string.wiadomosc_email11) + myszka + "\n\n" + getString(R.string.wiadomosc_email6) + klawa + "\n\n" + getString(R.string.wiadomosc_email7) + kamera + "\n\n" + getString(R.string.wiadomosc_email8) + koszt + "\n\n\n" + getString(R.string.wiadomosc_email9);

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mEmail, mSubject, mMessage);

        javaMailAPI.execute();
    }

    private void sendSms(){
        String numerTel = telefon.trim();
        String SMS =getString(R.string.szmeje_sms) + "\n" + getString(R.string.wiadomosc_email1) + imie + getString(R.string.wiadomosc_email2) + "\n" + getString(R.string.zlozono_sms);

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(numerTel, null, SMS, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void setAppLocale(String localeCode){
//        Resources resources = getResources();
//        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
//        Configuration configuration = resources.getConfiguration();
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1){
//            configuration.setLocale(new Locale(localeCode.toLowerCase()));
//        }else {
//            configuration.locale = new Locale(localeCode.toLowerCase());
//        }
//        resources.updateConfiguration(configuration, displayMetrics);
//    }

}
