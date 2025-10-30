package com.example.pc1_sem5_tdamb_2025ii_ilderguevara;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Objects;

public class AdvertenciaFragment extends Fragment {

    private TextInputEditText etDireccion;
    private TextInputEditText etTelefono;
    private Button btnConfirmarContacto;
    private TextView tvAdvertenciaTitulo;
    private TextInputLayout layoutDireccion;
    private TextInputLayout layoutTelefono;

    // --- CAMBIO 1: Declara 'resultado' aquí ---
    private String resultado;

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

        // Enlazar vistas
        etDireccion = view.findViewById(R.id.etDireccion);
        etTelefono = view.findViewById(R.id.etTelefono);
        btnConfirmarContacto = view.findViewById(R.id.btnConfirmarContacto);
        tvAdvertenciaTitulo = view.findViewById(R.id.tvAdvertenciaTitulo);
        layoutDireccion = view.findViewById(R.id.layoutDireccion);
        layoutTelefono = view.findViewById(R.id.layoutTelefono);
        TextView tvSubtitulo = view.findViewById(R.id.tvSubtitulo);

        // --- CAMBIO 2: Inicializa el campo (sin 'String' al inicio) ---
        resultado = "PENDIENTE"; // Valor por defecto
        if (getArguments() != null) {
            resultado = AdvertenciaFragmentArgs.fromBundle(getArguments()).getResultado();
        }

        if ("DESAPROBADO".equals(resultado)) {
            // Ocultar el formulario y cambiar el título
            tvAdvertenciaTitulo.setText("SOLICITUD DESAPROBADA");
            tvSubtitulo.setVisibility(View.GONE);
            layoutDireccion.setVisibility(View.GONE);
            layoutTelefono.setVisibility(View.GONE);
            btnConfirmarContacto.setText("VOLVER AL INICIO");
        }

        // El listener ahora puede acceder al campo 'resultado' sin error
        btnConfirmarContacto.setOnClickListener(v -> {
            if ("DESAPROBADO".equals(resultado)) {
                // Si está desaprobado, solo vuelve
                navegarDeVuelta();
            } else {
                // Si está pendiente, valida y luego vuelve
                handleConfirmarContacto();
            }
        });
    }

    private void handleConfirmarContacto() {
        String direccion = Objects.requireNonNull(etDireccion.getText()).toString().trim();
        String telefono = Objects.requireNonNull(etTelefono.getText()).toString().trim();

        // Validación de campos no vacíos
        if (direccion.isEmpty() || telefono.isEmpty()) {
            Toast.makeText(requireContext(), "Por favor, completa la dirección y el teléfono.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lógica de confirmación
        Toast.makeText(requireContext(), "Nos comunicaremos contigo al " + telefono + " pronto. Gracias.", Toast.LENGTH_LONG).show();

        // Navega de vuelta
        navegarDeVuelta();
    }

    /**
     * Método helper para navegar de vuelta a la pantalla de Solicitud
     */
    private void navegarDeVuelta() {
        NavController navController = Navigation.findNavController(requireView());
        navController.navigate(AdvertenciaFragmentDirections.actionAdvertenciaFragmentToSolicitudFragment());
    }
}
