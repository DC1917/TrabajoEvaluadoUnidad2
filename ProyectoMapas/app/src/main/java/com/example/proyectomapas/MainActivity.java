package com.example.proyectomapas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText usuarioEditText;
    private EditText contrasenaEditText;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioEditText=findViewById(R.id.usuario);
        contrasenaEditText=findViewById(R.id.contrasena);
        spinner=findViewById(R.id.spinnerRoles);
        String[] roles = {"Administrador", "Usuario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void onClickAcceder(View view){
        String user = usuarioEditText.getText().toString().trim();
        String pass = contrasenaEditText.getText().toString().trim();
        String rol = spinner.getSelectedItem().toString();
        if(user.isEmpty()){
            Toast.makeText(this, "El campo de usuario está vacío", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.isEmpty()){
            Toast.makeText(this, "El campo de contraseña está vacío", Toast.LENGTH_SHORT).show();
            return;
        }
        if (user.equals("nixo") && pass.equals("1234") && rol.equals("Administrador")) {
            Toast.makeText(this, "Redirigiendo a Administrador", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AccesoActivityad.class);
            startActivity(intent);
        } else if (user.equals("diego") && pass.equals("1234") && rol.equals("Usuario")) {
            Toast.makeText(this, "Redirigiendo a Usuario", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AccesoActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }

    }
    public void onClickRegistro(View view){
        Intent intent = new Intent(this, ActivityRegistro.class);
        startActivity(intent);
    }
    
    public void onClickMapa(View view) {
        Intent intent = new Intent(this, MapaActivity.class);
        startActivity(intent);
    }

}
