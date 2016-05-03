package es.upm.dit.adsw;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private int aciertos;
    private int fallos;
    private int suertes;
    public static final int OBTEN_PREGUNTA = 0;
    public static final int OBTEN_SUERTE = 1;
    private TextView aciertosTextView;
    private TextView fallosTextView;
    private TextView suertesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aciertos = 0;
        fallos = 0;
        suertes = 0;
        aciertosTextView = (TextView) findViewById(R.id.aciertos);
        fallosTextView = (TextView) findViewById(R.id.fallos);
        suertesTextView = (TextView) findViewById(R.id.suertes);

        aciertosTextView.setText("Aciertos " + aciertos);
        fallosTextView.setText("Fallos " + fallos);
        suertesTextView.setText("Suertes " + suertes);

        Button botonPregunta = (Button) findViewById(R.id.pregunta);
        botonPregunta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick pregunta");
                Intent intent = new Intent(MainActivity.this, PreguntaActivity.class);
                startActivityForResult(intent, OBTEN_PREGUNTA);
            }
        });

        Button botonSuerte = (Button) findViewById(R.id.suerte);
        botonSuerte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick suerte");
                Intent intent2 = new Intent(MainActivity.this, SuerteActivity.class);
                PackageManager manager = getBaseContext().getPackageManager();
                List<ResolveInfo> infos = manager.queryIntentActivities(intent2, 0);
                Log.d(TAG, "Infos " + infos.size());
                startActivityForResult(intent2, OBTEN_SUERTE);
            }
        });

        Button botonReset = (Button) findViewById(R.id.reset);
        botonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suertes = aciertos = fallos = 0;
                aciertosTextView.setText("Aciertos " + aciertos);
                fallosTextView.setText("Fallos " + fallos);
                suertesTextView.setText("Suertes " + suertes);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // respondemos al código
        switch(requestCode){
            case OBTEN_PREGUNTA: {
                if (resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    boolean acierto = data.getExtras().getBoolean("acierto");
                    if (acierto) {
                        aciertos++;
                    } else {
                        fallos++;
                    }
                    aciertosTextView.setText("Aciertos " + aciertos);
                    fallosTextView.setText("Fallos " + fallos);

                } else { // RESULT_CANCELED
                    Toast.makeText(getBaseContext(), "Usuario ha pasado la pregunta", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case OBTEN_SUERTE: {
                if (resultCode == RESULT_OK) {
                    boolean suerte = data.getExtras().getBoolean("suerte");
                    if (suerte) {
                        suertes++;
                    } else {
                        suertes--;
                    }
                    suertesTextView.setText("Suertes " + suertes);
                }
                break;
            }
            default: {
                Log.e(TAG, "Código inesperado " + requestCode);
                finish();
            }
        }
    }
}
