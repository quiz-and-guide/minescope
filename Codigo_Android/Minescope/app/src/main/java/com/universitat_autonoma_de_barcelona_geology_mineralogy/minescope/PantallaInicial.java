package com.universitat_autonoma_de_barcelona_geology_mineralogy.minescope;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class PantallaInicial extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_bienvenida);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(PantallaInicial.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}
