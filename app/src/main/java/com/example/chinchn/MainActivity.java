package com.example.chinchn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Jugadores2(View view){
        Intent sig = new Intent(this, NombreJugadores2.class);
        startActivity(sig);
    }

    public void Jugadores3(View view){
        Intent sig = new Intent(this, NombreJugadores3.class);
        startActivity(sig);
    }

    public void Jugadores4(View view){
        Intent sig = new Intent(this, NombreJugadores4.class);
        startActivity(sig);
    }
}