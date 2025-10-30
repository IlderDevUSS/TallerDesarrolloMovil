package com.example.pc1_sem5_tdamb_2025ii_ilderguevara;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class SolicitudFragment extends Fragment {

    private TextInputEditText etDni;
    private TextInputEditText etProfesion;
    private TextInputEditText etCiudad;
    private TextInputEditText etHijos;
    private TextInputEditText etSueldo;
    private Button btnProcesar;

    public SolicitudFragment() {
        // Constructor público vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.fragment_solicitud, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Enlazar componentes del layout
        etDni = view.findViewById(R.id.etDni);
        etProfesion = view.findViewById(R.id.etProfesion);
        etCiudad = view.findViewById(R.id.etCiudad);
        etHijos = view.findViewById(R.id.etHijos);
        etSueldo = view.findViewById(R.id.etSueldo);
        btnProcesar = view.findViewById(R.id.btnProcesar);

        // 2. Establecer el listener del botón
        btnProcesar.setOnClickListener(v -> handleProcesar());
    }

    /**
     * Valida los campos y procesa la solicitud para navegar a la siguiente pantalla.
     */
    private void handleProcesar() {
        // Obtener valores y limpiar espacios
        String dniStr = Objects.requireNonNull(etDni.getText()).toString().trim();
        String profesion = Objects.requireNonNull(etProfesion.getText()).toString().trim();
        String ciudad = Objects.requireNonNull(etCiudad.getText()).toString().trim();
        String hijosStr = Objects.requireNonNull(etHijos.getText()).toString().trim();
        String sueldoStr = Objects.requireNonNull(etSueldo.getText()).toString().trim();

        // 3. VALIDACIÓN DE CAMPOS NO VACÍOS (+3 puntos)
        if (dniStr.isEmpty() || profesion.isEmpty() || ciudad.isEmpty() || hijosStr.isEmpty() || sueldoStr.isEmpty()) {
            Toast.makeText(requireContext(), "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Conversión a números
        int hijos;
        double sueldo;
        try {
            hijos = Integer.parseInt(hijosStr);
            sueldo = Double.parseDouble(sueldoStr);
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Cantidad de Hijos y Sueldo deben ser números válidos.", Toast.LENGTH_LONG).show();
            return;
        }

        // 4. LÓGICA DE EVALUACIÓN (+5 puntos)
        String resultado;

        // Caso 1: DESAPROBADO
        if (hijos >= 5 && sueldo <= 1200.0) {
            resultado = "DESAPROBADO";
            // Caso 2: APROBADO
        } else if (hijos <= 2 && sueldo >= 5000.0) {
            resultado = "APROBADO";
            // Caso 3: PENDIENTE DE VISITA (Para todos los demás casos)
        } else {
            resultado = "PENDIENTE";
        }

        // 5. NAVEGACIÓN con Safe Args
        navigateToResultado(resultado);
    }

    /**
     * Navega al Fragmento de resultado usando Safe Args.
     */
    private void navigateToResultado(String resultado) {
        NavController navController = Navigation.findNavController(requireView());

        if (resultado.equals("APROBADO")) {
            navController.navigate(SolicitudFragmentDirections.actionSolicitudFragmentToAprobadoFragment());
        } else {
            // CORRECCIÓN: Pasamos el resultado (sea "PENDIENTE" o "DESAPROBADO")
            // Reconstruye el proyecto para que SolicitudFragmentDirections se actualice.
            SolicitudFragmentDirections.ActionSolicitudFragmentToAdvertenciaFragment action;
            action = SolicitudFragmentDirections.actionSolicitudFragmentToAdvertenciaFragment(resultado);
            navController.navigate(action);
        }
    }
}
