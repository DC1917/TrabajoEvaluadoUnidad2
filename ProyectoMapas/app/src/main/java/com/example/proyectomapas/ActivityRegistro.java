package com.example.proyectomapas;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityRegistro extends AppCompatActivity {
    // Declarar variables
    Spinner spSpinner;
    String[] genero = new String[]{"Masculino", "Femenino", "Otro"};
    EditText edtRut, edtUsuario, edtCorreo, edtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        // Defino los campos del formulario
        edtRut = findViewById(R.id.edtRut);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtClave = findViewById(R.id.edtClave);
        spSpinner = findViewById(R.id.spSpinner);

        // Poblar Spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genero);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSpinner.setAdapter(spinnerAdapter);
    }

    public void onClickAgregar(View view) {
        DataHelper dh = new DataHelper(this, "MercadoLivre.db", null, 1);
        SQLiteDatabase bd = dh.getWritableDatabase();
        ContentValues reg = new ContentValues();

        try {
            // Verifica que los campos no estén vacíos antes de intentar usarlos
            if (edtRut.getText().toString().isEmpty() || edtUsuario.getText().toString().isEmpty() ||
                    edtCorreo.getText().toString().isEmpty() || edtClave.getText().toString().isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                return; // Sal de la función si algún campo está vacío
            }

            // Convierte y agrega los valores a ContentValues
            reg.put("rut", Integer.parseInt(edtRut.getText().toString()));
            reg.put("nombreUsuario", edtUsuario.getText().toString());
            reg.put("correo", edtCorreo.getText().toString());
            reg.put("clave", edtClave.getText().toString());
            reg.put("genero", spSpinner.getSelectedItem().toString());

            long resp = bd.insert("Usuarios", null, reg);
            if (resp == -1) {
                Toast.makeText(this, "Error al insertar el registro", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show();
            }

            bd.close();
            limpiar();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "El campo RUT debe ser numérico", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    // Método para limpiar los campos de texto
    public void limpiar() {
        edtRut.setText("");
        edtUsuario.setText("");
        edtCorreo.setText("");
        edtClave.setText("");
    }
}

