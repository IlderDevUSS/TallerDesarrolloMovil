package com.example.ec_parcial_tdamb_2025ii_ilderguevara;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import com.example.ec_parcial_tdamb_2025ii_ilderguevara.databinding.FragmentRegistroBinding;
import java.util.Arrays;
import java.util.List;

public class RegistroFragment extends Fragment {

    private FragmentRegistroBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegistroBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSiguiente.setOnClickListener(v -> handleSiguiente(v));
    }

    private void handleSiguiente(View view) {

        String nombre = binding.etNombre.getText().toString().trim();
        String correo = binding.etCorreo.getText().toString().trim();
        String escuela = binding.etEscuela.getText().toString().trim();
        String deporte = binding.actvDeporte.getText().toString().toLowerCase();

        String sexo = ((RadioButton) binding.getRoot().findViewById(binding.rgSexo.getCheckedRadioButtonId()))
                .getText().toString();
        String tipoParticipante = ((RadioButton) binding.getRoot().findViewById(binding.rgTipoParticipante.getCheckedRadioButtonId()))
                .getText().toString();


        if (nombre.isEmpty() || correo.isEmpty() || escuela.isEmpty()) {
            Toast.makeText(getContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!correo.endsWith("@uss.edu.pe")) {
            Toast.makeText(getContext(), "El correo debe ser de dominio @uss.edu.pe", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> deportesValidos = Arrays.asList("fútbol", "volley", "ajedrez");
        if (!deportesValidos.contains(deporte)) {
            Toast.makeText(getContext(), "Deporte no válido. Seleccione fútbol, volley o ajedrez.", Toast.LENGTH_SHORT).show();
            return;
        }


        NavController navController = Navigation.findNavController(view);
        RegistroFragmentDirections.ActionRegistroFragmentToPagoFragment action =
                RegistroFragmentDirections.actionRegistroFragmentToPagoFragment(
                        nombre,
                        correo,
                        escuela,
                        sexo,
                        deporte,
                        tipoParticipante
                );
        navController.navigate(action);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}