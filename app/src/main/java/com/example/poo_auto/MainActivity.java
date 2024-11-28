package com.example.poo_auto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText etMarca, etModelo, etColor, etAge_fabricacion, etKilometraje, etPlaca;
    private Button btnEnviar, btnSalir, btnRevisar;
    private Spinner spinnerCombustible;
    private String combustibleSeleccionado;
    public static ArrayList<Vehiculo> vehiculosList = new ArrayList<>();  // Lista estática para guardar objetos Vehiculo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        REFERENCIAS();
        configurarSpinnerCombustible();
        SAlIR();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FUNCION_LEER();
            }
        });

        // Configurar el botón de "Revisar Registro"
        btnRevisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void REFERENCIAS() {
        etMarca = findViewById(R.id.etMarca);
        etModelo = findViewById(R.id.etModelo);
        etColor = findViewById(R.id.etColor);
        etAge_fabricacion = findViewById(R.id.etAge_fabricacion);
        etKilometraje = findViewById(R.id.etKilometraje);
        etPlaca = findViewById(R.id.etPlaca);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnSalir = findViewById(R.id.btnSalir);
        btnRevisar = findViewById(R.id.btnRevisar);
        spinnerCombustible = findViewById(R.id.spinnerCombustible);
    }

    private void SAlIR() {
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void FUNCION_LEER() {
        if (!validarCamposVacios()) {
            return;  // Si algún campo está vacío, terminamos la ejecución
        }

        // Obtener datos de los EditText
        String marca = etMarca.getText().toString();
        String modelo = etModelo.getText().toString();
        String color = etColor.getText().toString();
        String combustible = spinnerCombustible.getSelectedItem().toString();
        String placa = etPlaca.getText().toString();

        // Validar año de fabricación y kilometraje
        Integer ageFabricacion = validarEntradaNumerica(etAge_fabricacion, "Año de fabricación");
        if (ageFabricacion == null) return;

        Integer kilometraje = validarEntradaNumerica(etKilometraje, "Kilometraje");
        if (kilometraje == null) return;

        // Crear un objeto Vehiculo y agregarlo a la lista
        Vehiculo vehiculo = new Vehiculo(marca, modelo, color, combustible, ageFabricacion, kilometraje, placa);
        vehiculosList.add(vehiculo);  // Añadir el vehículo a la lista

        // Mostrar mensaje de éxito
        Toast.makeText(this, "Vehículo registrado", Toast.LENGTH_SHORT).show();
    }

    private boolean validarCamposVacios() {
        boolean esValido = true;

        // Verificar si los campos de texto están vacíos
        if (etMarca.getText().toString().isEmpty()) {
            etMarca.setError("Este campo es obligatorio");
            esValido = false;
        }
        if (etModelo.getText().toString().isEmpty()) {
            etModelo.setError("Este campo es obligatorio");
            esValido = false;
        }
        if (etColor.getText().toString().isEmpty()) {
            etColor.setError("Este campo es obligatorio");
            esValido = false;
        }
        if (etAge_fabricacion.getText().toString().isEmpty()) {
            etAge_fabricacion.setError("Este campo es obligatorio");
            esValido = false;
        }
        if (etKilometraje.getText().toString().isEmpty()) {
            etKilometraje.setError("Este campo es obligatorio");
            esValido = false;
        }
        if (etPlaca.getText().toString().isEmpty()) {
            etPlaca.setError("Este campo es obligatorio");
            esValido = false;
        }

        // Validar si se seleccionó un combustible
        if (spinnerCombustible.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Debe seleccionar un tipo de combustible", Toast.LENGTH_SHORT).show();
            esValido = false;
        }

        return esValido;
    }

    private Integer validarEntradaNumerica(EditText editText, String campo) {
        Integer valor = null;
        try {
            valor = Integer.parseInt(editText.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor, ingrese un valor numérico válido para " + campo, Toast.LENGTH_SHORT).show();
        }
        return valor;
    }

    private void configurarSpinnerCombustible() {
        // Cargar el adaptador para el Spinner con los combustibles desde strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.combustibles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCombustible.setAdapter(adapter);

        // Establecer un listener para capturar la selección del Spinner
        spinnerCombustible.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Obtener la opción seleccionada
                combustibleSeleccionado = parentView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // En caso de no seleccionar nada
                combustibleSeleccionado = "";
            }
        });
    }

    private void revisarRegistros() {
        // Mostrar todos los vehículos registrados
        StringBuilder registros = new StringBuilder();
        for (Vehiculo vehiculo : vehiculosList) {
            registros.append("Marca: ").append(vehiculo.getMarca()).append("\n");
            registros.append("Modelo: ").append(vehiculo.getModelo()).append("\n");
            registros.append("Color: ").append(vehiculo.getColor()).append("\n");
            registros.append("Combustible: ").append(vehiculo.getCombustible()).append("\n");
            registros.append("Año de fabricación: ").append(vehiculo.getAgeFabricacion()).append("\n");
            registros.append("Kilometraje: ").append(vehiculo.getKilometraje()).append("\n");
            registros.append("Placa: ").append(vehiculo.getPlaca()).append("\n\n");
        }

        // Mostrar los registros en un Toast o en un Log
        if (vehiculosList.isEmpty()) {
            Toast.makeText(this, "No hay vehículos registrados", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registros:\n" + registros.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
