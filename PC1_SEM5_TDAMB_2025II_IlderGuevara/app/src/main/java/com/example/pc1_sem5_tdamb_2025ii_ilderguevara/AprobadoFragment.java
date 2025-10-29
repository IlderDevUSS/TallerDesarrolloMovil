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
import android.widget.EditText;
import android.widget.Toast;

/**
 * Fragmento que se muestra si la solicitud es APROBADA (fondo verde).
 * Solicita el email del usuario para enviar la confirmación.
 */
public class AprobadoFragment extends Fragment {

    private EditText etEmail;
    private Button btnEnviar;

    public AprobadoFragment() {
        // Constructor público vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aprobado, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Inicialización de componentes
        etEmail = view.findViewById(R.id.etEmailAprobado);
        btnEnviar = view.findViewById(R.id.btnEnviarEmail);

        // 2. Listener para el botón Enviar
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleEnviarEmail(view);
            }
        });
    }

    /**
     * Valida el email y realiza la navegación de vuelta a la pantalla de solicitud.
     */
    private void handleEnviarEmail(View view) {
        String email = etEmail.getText().toString().trim();

        // 1. Validación de email vacío
        if (email.isEmpty()) {
            Toast.makeText(getContext(), "El email no puede estar vacío.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 2. Validación de formato simple (opcional, puedes agregar una regex más estricta si es necesario)
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getContext(), "Ingresa un formato de email válido.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 3. Indicación de envío y retorno a la pantalla de Solicitud
        Toast.makeText(getContext(), "Correo de felicitaciones enviado a: " + email, Toast.LENGTH_LONG).show();

        // Obtiene el controlador de navegación
        NavController navController = Navigation.findNavController(view);

        // Usa Safe Args para volver a la pantalla de Solicitud
        // Nota: El 'AprobadoFragmentDirections' ahora usa tu paquete real.
        navController.navigate(AprobadoFragmentDirections.actionAprobadoFragmentToSolicitudFragment());
    }
}
