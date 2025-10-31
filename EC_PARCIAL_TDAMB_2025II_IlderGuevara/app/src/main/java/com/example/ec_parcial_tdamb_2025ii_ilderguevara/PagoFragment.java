package com.example.ec_parcial_tdamb_2025ii_ilderguevara;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.ec_parcial_tdamb_2025ii_ilderguevara.databinding.FragmentPagoBinding;

public class PagoFragment extends Fragment {

    private FragmentPagoBinding binding;
    private PagoFragmentArgs navArgs;
    private double costo = 0.0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPagoBinding.inflate(inflater, container, false);

        navArgs = PagoFragmentArgs.fromBundle(getArguments());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        String tipo = navArgs.getTipoParticipante();

        if ("Jugador".equals(tipo)) {
            costo = 50.0;
            binding.pagoLayout.setBackgroundColor(Color.parseColor("#216DFA")); // Azul claro
            binding.tvTituloPago.setText("Suscripción Jugador");
            binding.tvCostoSuscripcion.setText("Costo: S/ 50.00");
        } else {
            costo = 100.0;
            binding.pagoLayout.setBackgroundColor(Color.parseColor("#26FA0F")); // Verde claro
            binding.tvTituloPago.setText("Entrada Espectador");
            binding.tvCostoSuscripcion.setText("Costo: S/ 100.00");
        }


        binding.etMontoIngresado.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    double montoIngresado = Double.parseDouble(s.toString());
                    if (montoIngresado > costo) {
                        double vuelto = montoIngresado - costo;
                        binding.tvVuelto.setText(String.format("Vuelto: S/ %.2f", vuelto));
                    } else {
                        binding.tvVuelto.setText("Vuelto: S/ 0.00");
                    }
                } catch (NumberFormatException e) {
                    binding.tvVuelto.setText("Vuelto: S/ 0.00");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });


        binding.btnPagar.setOnClickListener(v -> handlePagar(v));
    }

    private void handlePagar(View view) {
        double montoIngresado;
        try {
            montoIngresado = Double.parseDouble(binding.etMontoIngresado.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Ingresa un monto válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (montoIngresado < costo) {
            Toast.makeText(getContext(), "El monto ingresado es menor al costo (S/ " + costo + ")", Toast.LENGTH_SHORT).show();
            return;
        }


        NavController navController = Navigation.findNavController(view);
        PagoFragmentDirections.ActionPagoFragmentToResumenFragment action =
                PagoFragmentDirections.actionPagoFragmentToResumenFragment(
                        navArgs.getNombre(),
                        navArgs.getCorreo(),
                        navArgs.getTipoParticipante(),
                        (float) montoIngresado // SafeArgs pasa el monto como float
                );
        navController.navigate(action);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}