package com.example.ec_parcial_tdamb_2025ii_ilderguevara;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ec_parcial_tdamb_2025ii_ilderguevara.databinding.FragmentResumenBinding;

public class ResumenFragment extends Fragment {

    private FragmentResumenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResumenBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ResumenFragmentArgs navArgs = ResumenFragmentArgs.fromBundle(getArguments());

        String tipo = navArgs.getTipoParticipante();
        String correo = navArgs.getCorreo();
        String nombre = navArgs.getNombre();
        float montoPagado = navArgs.getMontoPagado();

        if ("Jugador".equals(tipo)) {
            binding.tvMensajeResumen.setText("Le enviaremos un email con las bases a su correo: \n" + correo);
            binding.tvDatosEspectador.setVisibility(View.GONE);
        } else {
            binding.tvMensajeResumen.setText("¡Registro Exitoso! \nResumen de sus datos:");
            String datos = String.format("Nombre: %s\nImporte Pagado: S/ %.2f", nombre, montoPagado);
            binding.tvDatosEspectador.setText(datos);
            binding.tvDatosEspectador.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}