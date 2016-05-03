package es.upm.dit.adsw;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class SuerteActivity extends AppCompatActivity {

    private static final String TAG = SuerteActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suerte);
        Log.d(TAG, "onCreate");
        TextView suerteTextView = (TextView) findViewById(R.id.textoSuerte);
        ImageView suerteImageView = (ImageView) findViewById(R.id.imagenSuerte);
        Button botonSuerte = (Button) findViewById(R.id.botonSuerte);

        Random random = new Random(System.currentTimeMillis());
        final boolean aleatorio = random.nextBoolean();
        if (aleatorio) {
            suerteTextView.setText("¡Has tenido buena suerte!");
            suerteImageView.setImageResource(R.drawable.buena_suerte);
            suerteImageView.setBackgroundColor(Color.GREEN);
        } else {
            suerteTextView.setText("¡Has tenido mala suerte!");
            suerteImageView.setImageResource(R.drawable.mala_suerte);
            suerteImageView.setBackgroundColor(Color.BLACK);
        }

        botonSuerte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("suerte", aleatorio);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
