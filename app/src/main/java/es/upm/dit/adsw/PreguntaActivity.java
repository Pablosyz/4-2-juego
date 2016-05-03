package es.upm.dit.adsw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PreguntaActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = PreguntaActivity.class.getName();
    private List<Pregunta> preguntas;
    private Pregunta pregunta;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        random = new Random(System.currentTimeMillis());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);
        preguntas = new ArrayList<>();
        generaPreguntas();
        TextView preguntaTextView = (TextView) findViewById(R.id.pregunta);
        Button botonAfirmativo = (Button) findViewById(R.id.afirmativo);
        Button botonNegativo = (Button) findViewById(R.id.negativo);
        Button botonPasar = (Button) findViewById(R.id.pasar);
        botonAfirmativo.setOnClickListener(this);
        botonNegativo.setOnClickListener(this);
        botonPasar.setOnClickListener(this);
        pregunta = getPregunta();
        preguntaTextView.setText(pregunta.getTexto());

    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick");
        boolean respuesta = false;
        switch(v.getId()) {
            case R.id.afirmativo: {
                respuesta = true;
                break;
            }
            case R.id.negativo: {
                respuesta = false;
                break;
            }
            case R.id.pasar: {
                setResult(RESULT_CANCELED);
                finish();
            }
            default:
                Log.e(TAG, "Click en vista no esperado");
        }

        boolean acierto = (respuesta == pregunta.isResultado());
        Intent intent = getIntent();
        intent.putExtra("acierto", acierto);
        setResult(RESULT_OK, intent);
        finish();
    }


    // Simula una base de datos
    private  void generaPreguntas(){
        Pregunta p1 = new Pregunta("¿Las cajas negras de los aviones son naranjas", true);
        Pregunta p2 = new Pregunta("Europa es un satélite de Júpiter", true);
        Pregunta p3 = new Pregunta("Sarajevo es la capital de Bielorrusia", false);
        Pregunta p4 = new Pregunta("Las islas Canarias están en el Mediterráneo", false);
        preguntas.add(p1);
        preguntas.add(p2);
        preguntas.add(p3);
        preguntas.add(p4);
    }

    private Pregunta getPregunta(){
        int num = random.nextInt(preguntas.size());
        return preguntas.get(num);

    }
}
