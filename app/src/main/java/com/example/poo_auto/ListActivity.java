package com.example.poo_auto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private TextView tvDetallesVehiculo;
    private Button btnAnterior, btnSiguiente, btnVolverRegistro;
    private ListView lvVehiculos;

    private ArrayList<Vehiculo> listaVehiculos;
    private int currentPosition = 0; // Para rastrear la posición actual del vehículo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Referencias a las vistas
        tvDetallesVehiculo = findViewById(R.id.tvDetallesVehiculo);
        btnAnterior = findViewById(R.id.btnAnterior);
        btnSiguiente = findViewById(R.id.btnSiguiente);
        btnVolverRegistro = findViewById(R.id.btnVolverRegistro);
        lvVehiculos = findViewById(R.id.lvVehiculos);

        // Obtener la lista de vehículos desde MainActivity
        listaVehiculos = MainActivity.vehiculosList;  // Lista estática de MainActivity

        // Configurar el ListView con un ArrayAdapter
        ArrayAdapter<Vehiculo> adapter = new ArrayAdapter<Vehiculo>(this, android.R.layout.simple_list_item_1, listaVehiculos) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                // Usar la vista por defecto para cada item
                View view = super.getView(position, convertView, parent);

                // Personalizar la vista si es necesario (en este caso no es necesario)
                return view;
            }
        };

        lvVehiculos.setAdapter(adapter);

        // Mostrar los detalles del primer vehículo
        mostrarDetallesVehiculo(currentPosition);

        // Configurar los botones de "Anterior" y "Siguiente"
        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition > 0) {
                    currentPosition--;
                    mostrarDetallesVehiculo(currentPosition);
                }
            }
        });

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition < listaVehiculos.size() - 1) {
                    currentPosition++;
                    mostrarDetallesVehiculo(currentPosition);
                }
            }
        });

        // Configurar el comportamiento cuando se haga clic en un ítem de la lista
        lvVehiculos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosition = position;
                mostrarDetallesVehiculo(currentPosition);
            }
        });

        // Configurar el botón "Volver a Registro"
        btnVolverRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Regresar a MainActivity (la actividad de registro)
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();  // Opcional: Finalizar ListActivity para que no quede en la pila de actividades
            }
        });
    }

    // Método para mostrar los detalles del vehículo en el TextView
    private void mostrarDetallesVehiculo(int position) {
        if (position >= 0 && position < listaVehiculos.size()) {
            Vehiculo vehiculo = listaVehiculos.get(position);
            String detalles = "Marca: " + vehiculo.getMarca() + "\n" +
                    "Modelo: " + vehiculo.getModelo() + "\n" +
                    "Color: " + vehiculo.getColor() + "\n" +
                    "Combustible: " + vehiculo.getCombustible() + "\n" +
                    "Año de Fabricación: " + vehiculo.getAgeFabricacion() + "\n" +
                    "Kilometraje: " + vehiculo.getKilometraje() + "\n" +
                    "Placa: " + vehiculo.getPlaca();
            tvDetallesVehiculo.setText(detalles);
        }
    }
}
