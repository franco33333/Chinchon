package com.example.chinchn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Jugadores4 extends AppCompatActivity {

    private TextView nombre1, nombre2, nombre3, nombre4, puntajeArb, turno;
    private EditText puntaje1, puntaje2, puntaje3, puntaje4;
    private boolean jugador1=true, jugador2=false, jugador3=false, terminado=false, jugador1Perdio=false, jugador2Perdio=false, jugador3Perdio=false, jugador4Perdio=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugadores4);

        nombre1 = (TextView)findViewById(R.id.jug1);
        nombre2 = (TextView)findViewById(R.id.jug2);
        nombre3 = (TextView)findViewById(R.id.jug3);
        nombre4 = (TextView)findViewById(R.id.jug4);
        puntajeArb = (TextView)findViewById(R.id.puntajeArbitriario);
        turno = (TextView)findViewById(R.id.turno);
        puntaje1 = (EditText)findViewById(R.id.puntosjug1);
        puntaje2 = (EditText)findViewById(R.id.puntosjug2);
        puntaje3 = (EditText)findViewById(R.id.puntosjug3);
        puntaje4 = (EditText)findViewById(R.id.puntosjug4);
        puntaje1.setKeyListener(null);
        puntaje2.setKeyListener(null);
        puntaje3.setKeyListener(null);
        puntaje4.setKeyListener(null);

        String nom1 = getIntent().getStringExtra("nombre1");
        String nom2 = getIntent().getStringExtra("nombre2");
        String nom3 = getIntent().getStringExtra("nombre3");
        String nom4 = getIntent().getStringExtra("nombre4");
        nombre1.setText(nom1);
        nombre2.setText(nom2);
        nombre3.setText(nom3);
        nombre4.setText(nom4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){ //Permite ka aparicion del menu superior derecho.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.opcionGuardar:
                //Aqui se deberia guardar los resultados en archivos.

            case R.id.opcionReiniciar:
                AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
                dialogo.setTitle("Importante");
                dialogo.setMessage("¿Estas seguro que quieres reiniciar la partida?");
                dialogo.setCancelable(false);
                dialogo.setPositiveButton("Seguro", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Jugadores4.this, "Reiniciado", Toast.LENGTH_SHORT).show();
                        puntaje1.setText("");
                        puntaje2.setText("");
                        puntaje3.setText("");
                        puntaje4.setText("");
                        puntajeArb.setText("");
                        turno.setText("Turno 1");
                        jugador1=true;
                        jugador2=false;
                        jugador3=false;
                        jugador1Perdio=false;
                        jugador2Perdio=false;
                        jugador3Perdio=false;
                        jugador4Perdio=false;
                        terminado=false;
                    }
                });
                dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        //Nada
                    }
                });
                dialogo.show();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void BotonMas(View view){
        if(!terminado){
            if(puntajeArb.getText().toString().equals("") || Integer.parseInt(puntajeArb.getText().toString())>101){
                Toast.makeText(this, "Debe ingresar un valor valido.", Toast.LENGTH_SHORT).show();
                puntajeArb.setText("");
                return;
            }
            int suma=0;
            int pos1=0, pos2=0;
            String texto="";
            if(jugador1){
                if(puntaje1.getText().toString().equals("")){
                    puntaje1.setText(puntajeArb.getText().toString()+'\n');
                }else{
                    pos1 = puntaje1.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje1.getText().toString().lastIndexOf('\n',pos1-1);

                    if(pos1<pos2)
                        texto = puntaje1.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje1.getText().toString().substring(pos2+1,pos1);
                    suma = Integer.parseInt(texto);
                    suma += Integer.parseInt(puntajeArb.getText().toString());
                    puntaje1.setText(puntaje1.getText().toString() + String.valueOf(suma)+'\n');
                    if(suma>=101){
                        //Perdedor jugador 1.
                        jugador1Perdio=true;
                    }
                }
                jugador1=false;
                if(!jugador2Perdio){
                    jugador2=true;
                    puntaje2.requestFocus();
                }else if(!jugador3Perdio){
                    jugador3=true;
                    puntaje3.requestFocus();
                }
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje2.setSelection(puntaje2.getText().length()); //Permite que autoscrolee el EditText
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());
            }else if(jugador2){
                if(puntaje2.getText().toString().equals("")){
                    puntaje2.setText(puntajeArb.getText().toString()+'\n');
                }else{
                    pos1 = puntaje2.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje2.getText().toString().lastIndexOf('\n',pos1-1);
                    if(pos1<pos2)
                        texto = puntaje2.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje2.getText().toString().substring(pos2+1,pos1);
                    suma = Integer.parseInt(texto);
                    suma += Integer.parseInt(puntajeArb.getText().toString());
                    puntaje2.setText(puntaje2.getText().toString() + String.valueOf(suma)+'\n');
                    if(suma>=101){
                        //Perdedor jugador 2.
                        jugador2Perdio=true;
                    }
                }
                jugador2=false;
                if(!jugador3Perdio){
                    jugador3=true;
                    puntaje3.requestFocus();
                }
                else if(!jugador4Perdio)
                    puntaje4.requestFocus();

                puntaje2.setSelection(puntaje2.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());
            }else if(jugador3){
                if(puntaje3.getText().toString().equals(""))
                    puntaje3.setText(puntajeArb.getText().toString()+'\n');
                else{
                    pos1 = puntaje3.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje3.getText().toString().lastIndexOf('\n', pos1-1);
                    if(pos1<pos2)
                        texto = puntaje3.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje3.getText().toString().substring(pos2+1,pos1);
                    suma = Integer.parseInt(texto);
                    suma +=Integer.parseInt(puntajeArb.getText().toString());
                    puntaje3.setText(puntaje3.getText().toString() + String.valueOf(suma)+'\n');
                    if(suma>=101){
                        //Perdedor jugador 3.
                        jugador3Perdio=true;
                    }
                }
                jugador3=false;
                if(!jugador4Perdio)
                    puntaje4.requestFocus();
                else if(!jugador1Perdio){
                    jugador1=true;
                    puntaje1.requestFocus();
                }else{
                    jugador2=true;
                    puntaje2.requestFocus();
                }
                puntaje2.setSelection(puntaje2.getText().length());
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());
            }else{
                if(puntaje4.getText().toString().equals(""))
                    puntaje4.setText(puntajeArb.getText().toString()+'\n');
                else{
                    pos1 = puntaje4.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje4.getText().toString().lastIndexOf('\n', pos1-1);
                    if(pos1<pos2)
                        texto = puntaje4.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje4.getText().toString().substring(pos2+1,pos1);
                    suma = Integer.parseInt(texto);
                    suma +=Integer.parseInt(puntajeArb.getText().toString());
                    puntaje4.setText(puntaje4.getText().toString() + String.valueOf(suma)+'\n');
                    if(suma>=101){
                        //Perdedor jugador 4.
                        jugador4Perdio=true;
                    }
                }

                if(!jugador1Perdio){
                    jugador1=true;
                    puntaje1.requestFocus();
                }
                else if(!jugador2Perdio){
                    jugador2=true;
                    puntaje2.requestFocus();
                }else{
                    jugador3=true;
                    puntaje3.requestFocus();
                }
                puntaje2.setSelection(puntaje2.getText().length());
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());

                texto = turno.getText().toString();
                pos1=texto.indexOf(' ');
                suma=Integer.parseInt(texto.substring(pos1+1))+1;
                turno.setText("Turno "+suma);
            }
            puntajeArb.setText("");

            //Corroboramos victoria.
            if(jugador1Perdio && jugador2Perdio && jugador4Perdio){
                puntaje3.setText(puntaje3.getText().toString() + "Ganador " + nombre3.getText().toString());
                terminado = true;
            }else if(jugador1Perdio && jugador3Perdio && jugador4Perdio){
                puntaje2.setText(puntaje2.getText().toString()+"Ganador "+nombre2.getText().toString());
                terminado=true;
            }else if(jugador3Perdio && jugador2Perdio && jugador4Perdio){
                puntaje1.setText(puntaje1.getText().toString() + "Ganador " + nombre1.getText().toString());
                terminado = true;
            }else if(jugador1Perdio && jugador2Perdio && jugador3Perdio){
                puntaje4.setText(puntaje4.getText().toString() + "Ganador " + nombre4.getText().toString());
                terminado=true;
            }
        }
    }

    public void BotonMenos10(View view){
        if(!terminado){
            int puntajeArb=-10;
            int pos1=0, pos2=0, resta;
            String texto="-10";
            if(jugador1){
                if(puntaje1.getText().toString().equals("")){
                    puntaje1.setText(String.valueOf(puntajeArb)+'\n');
                }else{
                    pos1 = puntaje1.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje1.getText().toString().lastIndexOf('\n',pos1-1);

                    if(pos1<pos2)
                        texto = puntaje1.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje1.getText().toString().substring(pos2+1,pos1);
                    resta = Integer.parseInt(texto);
                    resta += puntajeArb;
                    puntaje1.setText(puntaje1.getText().toString() + String.valueOf(resta)+'\n');
                }
                jugador1=false;
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());
                puntaje2.setSelection(puntaje2.getText().length()); //Permite que autoscrolee el EditText
                if(!jugador2Perdio){
                    jugador2=true;
                    puntaje2.requestFocus();
                }else if(!jugador3Perdio){
                    jugador3=true;
                    puntaje3.requestFocus();
                }else
                    puntaje4.requestFocus();
            }else if (jugador2){
                if(puntaje2.getText().toString().equals("")){
                    puntaje2.setText(String.valueOf(puntajeArb)+'\n');
                }else{
                    pos1 = puntaje2.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje2.getText().toString().lastIndexOf('\n',pos1-1);
                    if(pos1<pos2)
                        texto = puntaje2.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje2.getText().toString().substring(pos2+1,pos1);
                    resta = Integer.parseInt(texto);
                    resta += puntajeArb;
                    puntaje2.setText(puntaje2.getText().toString() + String.valueOf(resta)+'\n');
                }
                jugador2=false;
                puntaje2.setSelection(puntaje2.getText().length());
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());
                if(!jugador3Perdio){
                    jugador3=true;
                    puntaje3.requestFocus();
                }else if(!jugador4Perdio)
                    puntaje4.requestFocus();
                else {
                    jugador1=true;
                    puntaje1.requestFocus();
                }
            }else if(jugador3){
                if(puntaje3.getText().toString().equals("")){
                    puntaje3.setText(String.valueOf(puntajeArb)+'\n');
                }else{
                    pos1 = puntaje3.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje3.getText().toString().lastIndexOf('\n',pos1-1);
                    if(pos1<pos2)
                        texto = puntaje3.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje3.getText().toString().substring(pos2+1,pos1);
                    resta = Integer.parseInt(texto);
                    resta += puntajeArb;
                    puntaje3.setText(puntaje3.getText().toString() + String.valueOf(resta)+'\n');
                }
                jugador3=false;
                if(!jugador4Perdio)
                    puntaje4.requestFocus();
                else if(!jugador1Perdio){
                    jugador1=true;
                    puntaje1.requestFocus();
                }else{
                    jugador2=true;
                    puntaje2.requestFocus();
                }
                puntaje2.setSelection(puntaje2.getText().length());
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());
            }else{
                if(puntaje4.getText().toString().equals("")){
                    puntaje4.setText(String.valueOf(puntajeArb)+'\n');
                }else{
                    pos1 = puntaje4.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje4.getText().toString().lastIndexOf('\n',pos1-1);
                    if(pos1<pos2)
                        texto = puntaje4.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje4.getText().toString().substring(pos2+1,pos1);
                    resta = Integer.parseInt(texto);
                    resta += puntajeArb;
                    puntaje4.setText(puntaje4.getText().toString() + String.valueOf(resta)+'\n');
                }
                if(!jugador1Perdio){
                    jugador1=true;
                    puntaje1.requestFocus();
                }else if(!jugador2Perdio){
                    jugador2=true;
                    puntaje2.requestFocus();
                }else{
                    jugador3=true;
                    puntaje3.requestFocus();
                }
                puntaje2.setSelection(puntaje2.getText().length());
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());

                texto = turno.getText().toString();
                pos1=texto.indexOf(' ');
                resta=Integer.parseInt(texto.substring(pos1+1))+1;
                turno.setText("Turno "+resta);
            }
        }
    }

    public void BotonMenos25(View view){
        if(!terminado){
            int puntajeArb=-25;
            int pos1=0, pos2=0, resta;
            String texto="-10";
            if(jugador1){
                if(puntaje1.getText().toString().equals("")){
                    puntaje1.setText(String.valueOf(puntajeArb)+'\n');
                }else{
                    pos1 = puntaje1.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje1.getText().toString().lastIndexOf('\n',pos1-1);

                    if(pos1<pos2)
                        texto = puntaje1.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje1.getText().toString().substring(pos2+1,pos1);
                    resta = Integer.parseInt(texto);
                    resta += puntajeArb;
                    puntaje1.setText(puntaje1.getText().toString() + String.valueOf(resta)+'\n');
                }
                jugador1=false;
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());
                puntaje2.setSelection(puntaje2.getText().length()); //Permite que autoscrolee el EditText
                if(!jugador2Perdio){
                    jugador2=true;
                    puntaje2.requestFocus();
                }else if(!jugador3Perdio){
                    jugador3=true;
                    puntaje3.requestFocus();
                }else
                    puntaje4.requestFocus();
            }else if (jugador2){
                if(puntaje2.getText().toString().equals("")){
                    puntaje2.setText(String.valueOf(puntajeArb)+'\n');
                }else{
                    pos1 = puntaje2.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje2.getText().toString().lastIndexOf('\n',pos1-1);
                    if(pos1<pos2)
                        texto = puntaje2.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje2.getText().toString().substring(pos2+1,pos1);
                    resta = Integer.parseInt(texto);
                    resta += puntajeArb;
                    puntaje2.setText(puntaje2.getText().toString() + String.valueOf(resta)+'\n');
                }
                jugador2=false;
                puntaje2.setSelection(puntaje2.getText().length());
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());
                if(!jugador3Perdio){
                    jugador3=true;
                    puntaje3.requestFocus();
                }else if(!jugador4Perdio)
                    puntaje4.requestFocus();
                else {
                    jugador1=true;
                    puntaje1.requestFocus();
                }
            }else if(jugador3){
                if(puntaje3.getText().toString().equals("")){
                    puntaje3.setText(String.valueOf(puntajeArb)+'\n');
                }else{
                    pos1 = puntaje3.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje3.getText().toString().lastIndexOf('\n',pos1-1);
                    if(pos1<pos2)
                        texto = puntaje3.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje3.getText().toString().substring(pos2+1,pos1);
                    resta = Integer.parseInt(texto);
                    resta += puntajeArb;
                    puntaje3.setText(puntaje3.getText().toString() + String.valueOf(resta)+'\n');
                }
                jugador3=false;
                if(!jugador4Perdio)
                    puntaje4.requestFocus();
                else if(!jugador1Perdio){
                    jugador1=true;
                    puntaje1.requestFocus();
                }else{
                    jugador2=true;
                    puntaje2.requestFocus();
                }
                puntaje2.setSelection(puntaje2.getText().length());
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());
            }else{
                if(puntaje4.getText().toString().equals("")){
                    puntaje4.setText(String.valueOf(puntajeArb)+'\n');
                }else{
                    pos1 = puntaje4.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje4.getText().toString().lastIndexOf('\n',pos1-1);
                    if(pos1<pos2)
                        texto = puntaje4.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje4.getText().toString().substring(pos2+1,pos1);
                    resta = Integer.parseInt(texto);
                    resta += puntajeArb;
                    puntaje4.setText(puntaje4.getText().toString() + String.valueOf(resta)+'\n');
                }
                if(!jugador1Perdio){
                    jugador1=true;
                    puntaje1.requestFocus();
                }else if(!jugador2Perdio){
                    jugador2=true;
                    puntaje2.requestFocus();
                }else{
                    jugador3=true;
                    puntaje3.requestFocus();
                }
                puntaje2.setSelection(puntaje2.getText().length());
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());

                texto = turno.getText().toString();
                pos1=texto.indexOf(' ');
                resta=Integer.parseInt(texto.substring(pos1+1))+1;
                turno.setText("Turno "+resta);
            }
        }
    }

    public void BotonMenos50(View view){
        if(!terminado){
            int puntajeArb=-50;
            int pos1=0, pos2=0, resta;
            String texto="-10";
            if(jugador1){
                if(puntaje1.getText().toString().equals("")){
                    puntaje1.setText(String.valueOf(puntajeArb)+'\n');
                }else{
                    pos1 = puntaje1.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje1.getText().toString().lastIndexOf('\n',pos1-1);

                    if(pos1<pos2)
                        texto = puntaje1.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje1.getText().toString().substring(pos2+1,pos1);
                    resta = Integer.parseInt(texto);
                    resta += puntajeArb;
                    puntaje1.setText(puntaje1.getText().toString() + String.valueOf(resta)+'\n');
                }
                jugador1=false;
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());
                puntaje2.setSelection(puntaje2.getText().length()); //Permite que autoscrolee el EditText
                if(!jugador2Perdio){
                    jugador2=true;
                    puntaje2.requestFocus();
                }else if(!jugador3Perdio){
                    jugador3=true;
                    puntaje3.requestFocus();
                }else
                    puntaje4.requestFocus();
            }else if (jugador2){
                if(puntaje2.getText().toString().equals("")){
                    puntaje2.setText(String.valueOf(puntajeArb)+'\n');
                }else{
                    pos1 = puntaje2.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje2.getText().toString().lastIndexOf('\n',pos1-1);
                    if(pos1<pos2)
                        texto = puntaje2.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje2.getText().toString().substring(pos2+1,pos1);
                    resta = Integer.parseInt(texto);
                    resta += puntajeArb;
                    puntaje2.setText(puntaje2.getText().toString() + String.valueOf(resta)+'\n');
                }
                jugador2=false;
                puntaje2.setSelection(puntaje2.getText().length());
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());
                if(!jugador3Perdio){
                    jugador3=true;
                    puntaje3.requestFocus();
                }else if(!jugador4Perdio)
                    puntaje4.requestFocus();
                else {
                    jugador1=true;
                    puntaje1.requestFocus();
                }
            }else if(jugador3){
                if(puntaje3.getText().toString().equals("")){
                    puntaje3.setText(String.valueOf(puntajeArb)+'\n');
                }else{
                    pos1 = puntaje3.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje3.getText().toString().lastIndexOf('\n',pos1-1);
                    if(pos1<pos2)
                        texto = puntaje3.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje3.getText().toString().substring(pos2+1,pos1);
                    resta = Integer.parseInt(texto);
                    resta += puntajeArb;
                    puntaje3.setText(puntaje3.getText().toString() + String.valueOf(resta)+'\n');
                }
                jugador3=false;
                if(!jugador4Perdio)
                    puntaje4.requestFocus();
                else if(!jugador1Perdio){
                    jugador1=true;
                    puntaje1.requestFocus();
                }else{
                    jugador2=true;
                    puntaje2.requestFocus();
                }
                puntaje2.setSelection(puntaje2.getText().length());
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());
            }else{
                if(puntaje4.getText().toString().equals("")){
                    puntaje4.setText(String.valueOf(puntajeArb)+'\n');
                }else{
                    pos1 = puntaje4.getText().toString().lastIndexOf('\n');
                    pos2 = puntaje4.getText().toString().lastIndexOf('\n',pos1-1);
                    if(pos1<pos2)
                        texto = puntaje4.getText().toString().substring(0,pos2);
                    else
                        texto = puntaje4.getText().toString().substring(pos2+1,pos1);
                    resta = Integer.parseInt(texto);
                    resta += puntajeArb;
                    puntaje4.setText(puntaje4.getText().toString() + String.valueOf(resta)+'\n');
                }
                if(!jugador1Perdio){
                    jugador1=true;
                    puntaje1.requestFocus();
                }else if(!jugador2Perdio){
                    jugador2=true;
                    puntaje2.requestFocus();
                }else{
                    jugador3=true;
                    puntaje3.requestFocus();
                }
                puntaje2.setSelection(puntaje2.getText().length());
                puntaje1.setSelection(puntaje1.getText().length());
                puntaje3.setSelection(puntaje3.getText().length());
                puntaje4.setSelection(puntaje4.getText().length());

                texto = turno.getText().toString();
                pos1=texto.indexOf(' ');
                resta=Integer.parseInt(texto.substring(pos1+1))+1;
                turno.setText("Turno "+resta);
            }
        }
    }

    public void botonChinchon(View view){
        if(!terminado){
            String turno;
            if (jugador1)
                turno = nombre1.getText().toString();
            else if(jugador2)
                turno = nombre2.getText().toString();
            else if(jugador3)
                turno = nombre3.getText().toString();
            else
                turno = nombre4.getText().toString();

            AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            dialogo.setTitle("Confirmar");
            dialogo.setMessage("¿Estas seguro que el jugador "+turno+" hizo ChinChón?");
            dialogo.setCancelable(false);
            dialogo.setPositiveButton("Seguro", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(jugador1){
                        puntaje1.setText(puntaje1.getText().toString() + "Ganador " + nombre1.getText().toString() + "\nChinChón");
                        puntaje1.requestFocus();
                    }
                    else if(jugador2){
                        puntaje2.setText(puntaje2.getText().toString() + "Ganador " + nombre2.getText().toString() + "\nChinChón");
                        puntaje2.requestFocus();
                    }
                    else if(jugador3){
                        puntaje3.setText(puntaje3.getText().toString() + "Ganador " + nombre3.getText().toString() + "\nChinchón");
                        puntaje3.requestFocus();
                    }
                    else{
                        puntaje4.setText(puntaje4.getText().toString() + "Ganador " + nombre4.getText().toString() + "\nChinchón");
                        puntaje4.requestFocus();
                    }
                    terminado = true;
                    puntaje2.setSelection(puntaje2.getText().length());
                    puntaje1.setSelection(puntaje1.getText().length());
                    puntaje3.setSelection(puntaje3.getText().length());
                    puntaje4.setSelection(puntaje4.getText().length());
                }
            });
            dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    //Nada
                }
            });
            dialogo.show();
        }
    }

    public void Boton1(View view){
        puntajeArb.setText(puntajeArb.getText().toString()+'1');
    }

    public void Boton2(View view){
        puntajeArb.setText(puntajeArb.getText().toString()+'2');
    }

    public void Boton3(View view){
        puntajeArb.setText(puntajeArb.getText().toString()+'3');
    }

    public void Boton4(View view){
        puntajeArb.setText(puntajeArb.getText().toString()+'4');
    }

    public void Boton5(View view){
        puntajeArb.setText(puntajeArb.getText().toString()+'5');
    }

    public void Boton6(View view){
        puntajeArb.setText(puntajeArb.getText().toString()+'6');
    }

    public void Boton7(View view){
        puntajeArb.setText(puntajeArb.getText().toString()+'7');
    }

    public void Boton8(View view){
        puntajeArb.setText(puntajeArb.getText().toString()+'8');
    }

    public void Boton9(View view){
        puntajeArb.setText(puntajeArb.getText().toString()+'9');
    }

    public void Boton0(View view){
        puntajeArb.setText(puntajeArb.getText().toString()+'0');
    }

    public void botonBorrar(View view){
        if(puntajeArb.getText().toString().equals("")){
            return;
        }
        String cad =  puntajeArb.getText().toString().substring(0,puntajeArb.getText().toString().length()-1);
        puntajeArb.setText(cad);
    }
}