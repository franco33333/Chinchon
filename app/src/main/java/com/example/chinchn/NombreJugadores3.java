package com.example.chinchn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NombreJugadores3 extends AppCompatActivity {

    private EditText nom1, nom2, nom3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nombre_jugadores3);

        nom1 = (EditText)findViewById(R.id.nombre1);
        nom2 = (EditText)findViewById(R.id.nombre2);
        nom3 = (EditText)findViewById(R.id.nombre3);
    }

    public void Enviar(View view){
        if(nom1.getText().toString().equals("") || nom2.getText().toString().equals("") || nom3.getText().toString().equals("")){
            Toast.makeText(this, "Ingrese nombres válidos.", Toast.LENGTH_SHORT).show();
        }else {
            Intent sig = new Intent(this, Jugadores3.class);
            sig.putExtra("nombre1", nom1.getText().toString());
            sig.putExtra("nombre2", nom2.getText().toString());
            sig.putExtra("nombre3", nom3.getText().toString());
            startActivity(sig);
        }
    }
}