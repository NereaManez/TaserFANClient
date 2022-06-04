package es.ieslavereda.taserfan.entity;

import java.sql.Date;

public class Scooter extends Vehicle {
    private int numWheels;
    private float size;

    public Scooter(Type type, String matricula, float price, String marca, String descripcion, Color color, int bateria, State estado, String carnet, Date date, int numWheels, float size) {
        super(type, matricula, price, marca, descripcion, color, bateria, estado, carnet, date);
        this.numWheels = numWheels;
        this.size = size;
    }

    public int getNumWheels() {
        return numWheels;
    }

    public void setNumWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return super.toString() + "Scooter" +
                "numWheels=" + numWheels +
                ", size=" + size +
                '}';
    }
}
