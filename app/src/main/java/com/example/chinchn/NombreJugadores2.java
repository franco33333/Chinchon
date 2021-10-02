package com.example.chinchn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NombreJugadores2 extends AppCompatActivity {

    private EditText nom1, nom2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre_jugadores2);

        nom1 = (EditText)findViewById(R.id.nombre1);
        nom2 = (EditText)findViewById(R.id.nombre3);
    }

    public void Enviar(View view){
        if(nom1.getText().toString().equals("") || nom2.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese nombres válidos.", Toast.LENGTH_SHORT).show();
        }else {
            Intent sig = new Intent(this, Jugadores2.class);
            sig.putExtra("nombre1", nom1.getText().toString());
            sig.putExtra("nombre2", nom2.getText().toString());
            startActivity(sig);
        }
    }
}