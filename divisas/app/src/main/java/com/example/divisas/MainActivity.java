package com.example.divisas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String[] datos =new String[]{"DOLAR","EURO","PESO MEXICANO", "RUBLO","YEN","WON","LIBRA","RUPIA","PESO ARGENTINO","YUAN"};
    private Spinner  monedaActualSP;
    private Spinner  monedaCambioSP;
    private EditText valorCambioET;
    private TextView result;
    //private Button procesarCambio;

//VALORES DE CAMBIO CON EL DOLAR
    final private double DolarPesoMexicano = 20.24;
    final private double DolarEuro = 0.87;
    final private double DolarRublo= 70.90;
    final private double DolarYEN = 114.30;
    final private double DolarWon = 1175;
    final private double DolarLibraEsterlina = 0.72;
    final private double DolarRupia = 75.10;
    final private double DolarPesoArgentino = 99.30;
    final private double DolarYuan = 6.38;

    //VALORES DE CAMBIO CON EURO
    final private double EuroPesoMexicano = 23.57;
    final private double EuroRublo= 82.51;
    final private double EuroYEN = 133;
    final private double EuroWon = 1368;
    final private double EuroLibraEsterlina = 0.84;
    final private double EuroRupia = 87.38;
    final private double EuroPesoArgentino = 115.53;
    final private double EuroYuan = 7.43;

    //VALORES DE CAMBIO CON PESO MEXICANO
    final private double PesoMexicanoRublo = 3.50;
    final private double PesoMexicanoYuan= 0.32;
    final private double PesoMexicanoYEN = 5.64;
    final private double PesoMexicanoLibraEsterlina = 0.036;
    final private double PesoMexicanoWon = 58.02;
    final private double PesoMexicanoRupia = 3.71;
    final private double PesoMexicanoPesoArgentino = 4.90;

    //VALORES DE CAMBIO CON PESO ARGENTINO
    final private double PesoArgentinoRublo = 0.71;
    final private double PesoArgentinoYuan= 0.064;
    final private double PesoArgentinoYEN = 1.15;
    final private double PesoArgentinoLibraEsterlina = 0.0073;
    final private double PesoArgentinoWon = 11.84;
    final private double PesoArgentinoRupia = 0.76;

    //VALORES DE CAMBIO CON RUBLO
    final private double RubloYuan= 0.09;
    final private double RubloYEN = 1.61;
    final private double RubloLibraEsterlina = 0.010;
    final private double RubloWon = 16.58;
    final private double RubloRupia = 1.06;

    //VALORES DE CAMBIO CON YEN
    final private double YenYuan= 0.056;
    final private double YenLibraEsterlina = 0.0063;
    final private double YenWon = 10.29;
    final private double YenRupia = 0.66;

    //VALORES DE CAMBIO CON WON
    final private double WonLibraEsterlina = 0.00062;
    final private double WonRupia = 0.064;
    final private double WonYuan= 0.0054;

    //VALORES DE CAMBIO CON YUAN
    final private double YuanLibraEsterlina = 0.11;
    final private double YuanRupia = 11.77;

    //VALORES DE CAMBIO CON RUPIA
    final private double RupiaLibraEsterlina = 0.0097;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,datos);
        monedaActualSP=(Spinner) findViewById(R.id.monedaActualSP);
        monedaCambioSP=(Spinner) findViewById(R.id.monedaCambioSP);
        monedaActualSP.setAdapter(adapter);
        monedaCambioSP.setAdapter(adapter);
        SharedPreferences preferencias =getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        String tmpMonedaActual = preferencias.getString("monedaActual","");
        String tmpMonedaCambio =preferencias.getString("monedaCambio","");

        if(!tmpMonedaActual.equals("")){
            int indice= adapter.getPosition(tmpMonedaActual);
            monedaActualSP.setSelection(indice);
        }
        if(!tmpMonedaCambio.equals("")){
            int indice =adapter.getPosition(tmpMonedaCambio);
            monedaCambioSP.setSelection(indice);
        }

    }
    public void clickConvertir(View v){
        monedaActualSP=(Spinner) findViewById(R.id.monedaActualSP);
        monedaCambioSP=(Spinner) findViewById(R.id.monedaCambioSP);
        valorCambioET= (EditText) findViewById(R.id.valorCambioET);
        result=(TextView) findViewById(R.id.result);

        String monedaActual=monedaActualSP.getSelectedItem().toString();
        String monedaCambio=monedaCambioSP.getSelectedItem().toString();

        double valorCambio= Double.parseDouble(valorCambioET.getText().toString());
        double resultado= procesarConversion(monedaActual,monedaCambio,valorCambio);
        if(resultado > 0){
            result.setText(String.format("Por %5.2f %s, usted recibirá %5.2f %s",valorCambio,monedaActual,resultado,monedaCambio));
            valorCambioET.setText("");
            SharedPreferences preferencias = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferencias.edit();

            editor.putString("monedaActual",monedaActual);
            editor.putString("monedaCambio",monedaCambio);

            editor.commit();

        }
        else{
            result.setText(String.format("Usted recibirá"));
            Toast.makeText(MainActivity.this,"Las opciones elegidas no tienen valor de conversión",Toast.LENGTH_SHORT).show();
        }
    }
    private double procesarConversion(String monedaActual, String monedaCambio, double valorCambio){
        double resultadoConversión=0;
        switch(monedaActual){

            case"DOLAR":
                if(monedaCambio.equals("EURO")){
                    resultadoConversión = valorCambio * DolarEuro;
                }else if(monedaCambio.equals("PESO MEXICANO")){
                    resultadoConversión= valorCambio * DolarPesoMexicano;
                }
                else if(monedaCambio.equals("PESO ARGENTINO")){
                    resultadoConversión=valorCambio * DolarPesoArgentino;
                }
                else if(monedaCambio.equals("RUBLO")){
                    resultadoConversión=valorCambio * DolarRublo;
                }
                else if(monedaCambio.equals("YEN")){
                    resultadoConversión=valorCambio * DolarYEN;
                }
                else if(monedaCambio.equals("WON")){
                    resultadoConversión=valorCambio * DolarWon;
                }
                else if(monedaCambio.equals("LIBRA")){
                    resultadoConversión=valorCambio * DolarLibraEsterlina;
                }
                else if(monedaCambio.equals("RUPIA")){
                    resultadoConversión=valorCambio * DolarRupia;
                }
                else if(monedaCambio.equals("YUAN")){
                    resultadoConversión=valorCambio * DolarYuan;
                }
                break;

            case "EURO":
                if(monedaCambio.equals("DOLAR")){
                    resultadoConversión = valorCambio / DolarEuro;
                }else if(monedaCambio.equals("PESO MEXICANO")){
                    resultadoConversión= valorCambio * EuroPesoMexicano;
                }
                else if(monedaCambio.equals("PESO ARGENTINO")){
                    resultadoConversión=valorCambio * EuroPesoArgentino;
                }
                else if(monedaCambio.equals("RUBLO")){
                    resultadoConversión=valorCambio * EuroRublo;
                }
                else if(monedaCambio.equals("YEN")){
                    resultadoConversión=valorCambio * EuroYEN;
                }
                else if(monedaCambio.equals("WON")){
                    resultadoConversión=valorCambio * EuroWon;
                }
                else if(monedaCambio.equals("LIBRA")){
                    resultadoConversión=valorCambio * EuroLibraEsterlina;
                }
                else if(monedaCambio.equals("RUPIA")){
                    resultadoConversión=valorCambio * EuroRupia;
                }
                else if(monedaCambio.equals("YUAN")){
                    resultadoConversión=valorCambio * EuroYuan;
                }
                break;

            case "PESO MEXICANO":
                if(monedaCambio.equals("DOLAR")){
                    resultadoConversión = valorCambio / DolarPesoMexicano;
                }else if(monedaCambio.equals("EURO")){
                    resultadoConversión= valorCambio / EuroPesoMexicano;
                }
                else if(monedaCambio.equals("PESO ARGENTINO")){
                    resultadoConversión=valorCambio * PesoMexicanoPesoArgentino;
                }
                else if(monedaCambio.equals("RUBLO")){
                    resultadoConversión=valorCambio * PesoMexicanoRublo;
                }
                else if(monedaCambio.equals("YEN")){
                    resultadoConversión=valorCambio * PesoMexicanoYEN;
                }
                else if(monedaCambio.equals("WON")){
                    resultadoConversión=valorCambio * PesoMexicanoWon;
                }
                else if(monedaCambio.equals("LIBRA")){
                    resultadoConversión=valorCambio * PesoMexicanoLibraEsterlina;
                }
                else if(monedaCambio.equals("RUPIA")){
                    resultadoConversión=valorCambio * PesoMexicanoRupia;
                }
                else if(monedaCambio.equals("YUAN")){
                    resultadoConversión=valorCambio * PesoMexicanoYuan;
                }
                break;

            case "PESO ARGENTINO":
                if(monedaCambio.equals("DOLAR")){
                    resultadoConversión = valorCambio / DolarPesoArgentino;
                }else if(monedaCambio.equals("EURO")){
                    resultadoConversión= valorCambio / EuroPesoArgentino;
                }
                else if(monedaCambio.equals("PESO MEXICANO")){
                    resultadoConversión=valorCambio / PesoMexicanoPesoArgentino;
                }
                else if(monedaCambio.equals("RUBLO")){
                    resultadoConversión=valorCambio * PesoArgentinoRublo;
                }
                else if(monedaCambio.equals("YEN")){
                    resultadoConversión=valorCambio * PesoArgentinoYEN;
                }
                else if(monedaCambio.equals("WON")){
                    resultadoConversión=valorCambio * PesoArgentinoWon;
                }
                else if(monedaCambio.equals("LIBRA")){
                    resultadoConversión=valorCambio * PesoArgentinoLibraEsterlina;
                }
                else if(monedaCambio.equals("RUPIA")){
                    resultadoConversión=valorCambio * PesoArgentinoRupia;
                }
                else if(monedaCambio.equals("YUAN")){
                    resultadoConversión=valorCambio * PesoArgentinoYuan;
                }
                break;

            case "RUBLO":
                if(monedaCambio.equals("DOLAR")){
                    resultadoConversión = valorCambio / DolarRublo;
                }else if(monedaCambio.equals("EURO")){
                    resultadoConversión= valorCambio / EuroRublo;
                }
                else if(monedaCambio.equals("PESO MEXICANO")){
                    resultadoConversión=valorCambio / PesoMexicanoRublo;
                }
                else if(monedaCambio.equals("PESO ARGENTINO")){
                    resultadoConversión=valorCambio / PesoArgentinoRublo;
                }
                else if(monedaCambio.equals("YEN")){
                    resultadoConversión=valorCambio * RubloYEN;
                }
                else if(monedaCambio.equals("WON")){
                    resultadoConversión=valorCambio * RubloWon;
                }
                else if(monedaCambio.equals("LIBRA")){
                    resultadoConversión=valorCambio * RubloLibraEsterlina;
                }
                else if(monedaCambio.equals("RUPIA")){
                    resultadoConversión=valorCambio * RubloRupia;
                }
                else if(monedaCambio.equals("YUAN")){
                    resultadoConversión=valorCambio * RubloYuan;
                }
                break;

            case "YEN":
                if(monedaCambio.equals("DOLAR")){
                    resultadoConversión = valorCambio / DolarYEN;
                }else if(monedaCambio.equals("EURO")){
                    resultadoConversión= valorCambio / EuroYEN;
                }
                else if(monedaCambio.equals("PESO MEXICANO")){
                    resultadoConversión=valorCambio / PesoMexicanoYEN;
                }
                else if(monedaCambio.equals("PESO ARGENTINO")){
                    resultadoConversión=valorCambio / PesoArgentinoYEN;
                }
                else if(monedaCambio.equals("RUBLO")){
                    resultadoConversión=valorCambio / RubloYEN;
                }
                else if(monedaCambio.equals("WON")){
                    resultadoConversión=valorCambio * YenWon;
                }
                else if(monedaCambio.equals("LIBRA")){
                    resultadoConversión=valorCambio * YenLibraEsterlina;
                }
                else if(monedaCambio.equals("RUPIA")){
                    resultadoConversión=valorCambio * YenRupia;
                }
                else if(monedaCambio.equals("YUAN")){
                    resultadoConversión=valorCambio * YenYuan;
                }
                break;

            case "WON":
                if(monedaCambio.equals("DOLAR")){
                    resultadoConversión = valorCambio / DolarWon;
                }else if(monedaCambio.equals("EURO")){
                    resultadoConversión= valorCambio / EuroWon;
                }
                else if(monedaCambio.equals("PESO MEXICANO")){
                    resultadoConversión=valorCambio / PesoMexicanoWon;
                }
                else if(monedaCambio.equals("PESO ARGENTINO")){
                    resultadoConversión=valorCambio / PesoArgentinoWon;
                }
                else if(monedaCambio.equals("RUBLO")){
                    resultadoConversión=valorCambio / RubloWon;
                }
                else if(monedaCambio.equals("YEN")){
                    resultadoConversión=valorCambio / YenWon;
                }
                else if(monedaCambio.equals("LIBRA")){
                    resultadoConversión=valorCambio * WonLibraEsterlina;
                }
                else if(monedaCambio.equals("RUPIA")){
                    resultadoConversión=valorCambio * WonRupia;
                }
                else if(monedaCambio.equals("YUAN")){
                    resultadoConversión=valorCambio * WonYuan;
                }
                break;
            case "YUAN":
                if(monedaCambio.equals("DOLAR")){
                    resultadoConversión = valorCambio / DolarYuan;
                }else if(monedaCambio.equals("EURO")){
                    resultadoConversión= valorCambio / EuroYuan;
                }
                else if(monedaCambio.equals("PESO MEXICANO")){
                    resultadoConversión=valorCambio / PesoMexicanoYuan;
                }
                else if(monedaCambio.equals("PESO ARGENTINO")){
                    resultadoConversión=valorCambio / PesoArgentinoYuan;
                }
                else if(monedaCambio.equals("RUBLO")){
                    resultadoConversión=valorCambio / RubloYuan;
                }
                else if(monedaCambio.equals("YEN")){
                    resultadoConversión=valorCambio / YenYuan;
                }
                else if(monedaCambio.equals("LIBRA")){
                    resultadoConversión=valorCambio * YuanLibraEsterlina;
                }
                else if(monedaCambio.equals("RUPIA")){
                    resultadoConversión=valorCambio * YuanRupia;
                }
                else if(monedaCambio.equals("Won")){
                    resultadoConversión=valorCambio / WonYuan;
                }
                break;
            case "RUPIA":
                if(monedaCambio.equals("DOLAR")){
                    resultadoConversión = valorCambio / DolarRupia;
                }else if(monedaCambio.equals("EURO")){
                    resultadoConversión= valorCambio / EuroRupia;
                }
                else if(monedaCambio.equals("PESO MEXICANO")){
                    resultadoConversión=valorCambio / PesoMexicanoRupia;
                }
                else if(monedaCambio.equals("PESO ARGENTINO")){
                    resultadoConversión=valorCambio / PesoArgentinoRupia;
                }
                else if(monedaCambio.equals("RUBLO")){
                    resultadoConversión=valorCambio / RubloRupia;
                }
                else if(monedaCambio.equals("YEN")){
                    resultadoConversión=valorCambio / YenRupia;
                }
                else if(monedaCambio.equals("LIBRA")){
                    resultadoConversión=valorCambio * RupiaLibraEsterlina;
                }
                else if(monedaCambio.equals("YUAN")){
                    resultadoConversión=valorCambio / YuanRupia;
                }
                else if(monedaCambio.equals("WON")){
                    resultadoConversión=valorCambio / WonRupia;
                }
                break;
            case "LIBRA":
                if(monedaCambio.equals("DOLAR")){
                    resultadoConversión = valorCambio / DolarLibraEsterlina;
                }else if(monedaCambio.equals("EURO")){
                    resultadoConversión= valorCambio / EuroLibraEsterlina;
                }
                else if(monedaCambio.equals("PESO MEXICANO")){
                    resultadoConversión=valorCambio / PesoMexicanoLibraEsterlina;
                }
                else if(monedaCambio.equals("PESO ARGENTINO")){
                    resultadoConversión=valorCambio / PesoArgentinoLibraEsterlina;
                }
                else if(monedaCambio.equals("RUBLO")){
                    resultadoConversión=valorCambio / RubloLibraEsterlina;
                }
                else if(monedaCambio.equals("YEN")){
                    resultadoConversión=valorCambio / YenLibraEsterlina;
                }
                else if(monedaCambio.equals("YUAN")){
                    resultadoConversión=valorCambio / YuanLibraEsterlina;
                }
                else if(monedaCambio.equals("Won")){
                    resultadoConversión=valorCambio / WonLibraEsterlina;
                }
                else if(monedaCambio.equals("RUPIA")){
                    resultadoConversión=valorCambio / RupiaLibraEsterlina;
                }
                break;
        }
        return resultadoConversión;
    }
}