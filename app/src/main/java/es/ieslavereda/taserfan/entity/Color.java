package es.ieslavereda.taserfan.entity;

import java.util.Locale;

public enum Color {
    ROJO("rojo"), VERDE("verde"), AMARILLO("amarillo"), BLANCO("blanco"), NEGRO("negro"), AZUL("azul");

    private String color;
    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
