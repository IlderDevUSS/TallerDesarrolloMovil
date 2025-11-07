package com.example.tdama_202502_jecm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tdama_202502_jecm.Model.RequestAuth;
import com.example.tdama_202502_jecm.Model.ResponseAuth;
import com.example.tdama_202502_jecm.databinding.ActivityLoginBinding;
import com.example.tdama_202502_jecm.Interface.ByRedliPyAnyApi;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.nextButton.setOnClickListener(view -> {
            if (!isPasswordValid(binding.passwordEditText.getText())) {
                binding.passwordTextInput.setError(getString(R.string.shr_error_password));
            } else {
                binding.passwordTextInput.setError(null); // Clear the error
                llamarApiAuth();

            }
        });

        binding.passwordEditText.setOnKeyListener((view, i, keyEvent) -> {
            if (isPasswordValid(binding.passwordEditText.getText())) {
                binding.passwordTextInput.setError(null); //Clear the error
            }
            return false;
        });

        EdgeToEdge.enable(this);
        // setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 6;
    }

    private void llamarApiAuth() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://byRedli.pythonanywhere.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ByRedliPyAnyApi byRedliPyAnyApi = retrofit.create(ByRedliPyAnyApi.class);

        RequestAuth requestAuth = new RequestAuth(
                binding.usernameEditText.getText().toString(),
                binding.passwordEditText.getText().toString()
        );
        ///
        /*Metodo del profe
        RequestAuth requestAuth = new RequestAuth();
        requestAuth.setUsername(binding.usernameEditText.getText().toString());
        requestAuth.setPassword(binding.passwordEditText.getText().toString());
         */
        Call<ResponseAuth> call = byRedliPyAnyApi.obtenerToken(requestAuth);
        call.enqueue(new Callback<ResponseAuth>() {
            @Override
            public void onResponse(Call<ResponseAuth> call, Response<ResponseAuth> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getBaseContext(), "Código de error: " + response.code(), Toast.LENGTH_LONG).show();
                }
                ResponseAuth responseAuth = response.body();
                String token = responseAuth.getAccess_token();
                // Almacenar el dato de username en un SharedPreferences,
                // que sirva para toda la aplicación
                SharedPreferences sharedPreferences = getSharedPreferences("SP_USSAPP", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("csp_username", binding.usernameEditText.getText().toString());
                editor.putString("csp_token", token);
                editor.apply();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseAuth> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}