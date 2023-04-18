package com.example.mimenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mimenu.databinding.ActivityLogin2Binding;
import com.example.mimenu.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private LoginActivityViewModel vm;
    private Button btnInicio;
    private ActivityLogin2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogin2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);

        btnInicio = binding.btnIniciaSesion;

        btnInicio.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String email = binding.etEmail.getText().toString();
                String pass = binding.etPassword.getText().toString();

                if(vm.validarDatos(email,pass)) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "Email o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}