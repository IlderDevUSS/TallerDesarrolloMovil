package com.example.pc1_sem5_tdamb_2025ii_ilderguevara;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Actividad principal de la aplicación.
 * Esta actividad actúa como el contenedor principal (Host)
 * para el NavHostFragment definido en activity_main.xml.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Carga el layout activity_main.xml, que contiene el contenedor de navegación (NavHostFragment).
        setContentView(R.layout.activity_main);
    }
}

