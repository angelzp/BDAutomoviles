package com.example.angel.bdautomoviles.modelos;

/**
 * Created by Angel on 12/05/2015.
 */
public class autos {

    private String serie, marca, modelo, color;

    public autos(String serie, String marca, String modelo, String color) {
        this.serie = serie;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }

    public String getSerie() {
        return serie;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getColor() {
        return color;
    }
}
