package com.example.poo_auto;
import java.io.Serializable;

public class Vehiculo implements Serializable {
    // Atributos de la clase Vehiculo en mayúscula
    private String MARCA;
    private String MODELO;
    private String COLOR;
    private String COMBUSTIBLE;
    private int AGE_FABRICACION;
    private int KILOMETRAJE;
    private String PLACA;

    // Constructor de la clase Vehiculo
    public Vehiculo(String MARCA, String MODELO, String COLOR, String COMBUSTIBLE, int AGE_FABRICACION, int KILOMETRAJE, String PLACA) {
        this.MARCA = MARCA;
        this.MODELO = MODELO;
        this.COLOR = COLOR;
        this.COMBUSTIBLE = COMBUSTIBLE;
        this.AGE_FABRICACION = AGE_FABRICACION;
        this.KILOMETRAJE = KILOMETRAJE;
        this.PLACA = PLACA;
    }
    // Métodos Getter para cada atributo

    public String getMarca() {
        return MARCA;
    }

    public String getModelo() {
        return MODELO;
    }

    public String getColor() {
        return COLOR;
    }

    public String getCombustible() {
        return COMBUSTIBLE;
    }

    public int getAgeFabricacion() {
        return AGE_FABRICACION;
    }

    public int getKilometraje() {
        return KILOMETRAJE;
    }

    public String getPlaca() {
        return PLACA;
    }
    @Override
    public String toString() {
        // Retorna una representación legible del vehículo, que será lo que aparezca en la lista
        return MARCA + " " + MODELO + " (" + COLOR + ")";
    }
}
