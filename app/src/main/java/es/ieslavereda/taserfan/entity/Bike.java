package es.ieslavereda.taserfan.entity;

import java.sql.Date;

public class Bike extends Vehicle {
    private String tipo;

    public Bike(Type type, String matricula, float price, String marca, String descripcion, Color color, int bateria, State estado, Date date, String carnet, String tipo) {
        super(type, matricula, price, marca, descripcion, color, bateria, estado, carnet, date);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString() + "Bike{" +
                "type='" + tipo + '\'' +
                '}';
    }
}
