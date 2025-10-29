package com.example.pc1_sem5_tdamb_2025ii_ilderguevara;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class AdvertenciaFragment extends Fragment {

    private TextInputEditText etDireccion;
    private TextInputEditText etTelefono;
    private Button btnConfirmarContacto;

    public AdvertenciaFragment() {
        // Constructor requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_advertencia, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etDireccion = view.findViewById(R.id.etDireccion);
        etTelefono = view.findViewById(R.id.etTelefono);
        btnConfirmarContacto = view.findViewById(R.id.btnConfirmarContacto);

        btnConfirmarContacto.setOnClickListener(v -> handleConfirmarContacto());
    }

    private void handleConfirmarContacto() {
        String direccion = Objects.requireNonNull(etDireccion.getText()).toString().trim();
        String telefono = Objects.requireNonNull(etTelefono.getText()).toString().trim();

        // Validación de campos no vacíos (+3 puntos)
        if (direccion.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, completa la dirección y el teléfono.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lógica de confirmación
        Toast.makeText(requireContext(), "Nos comunicaremos contigo al " + telefono + " pronto. Gracias.", Toast.LENGTH_LONG).show();

        // No se pide volver a la pantalla de solicitud si no es APROBADO,
        // por lo que simplemente mostramos el Toast y la pantalla permanece.
    }
}
