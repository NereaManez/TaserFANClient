package es.ieslavereda.taserfan.entity;

import java.sql.Date;

public class Car extends Vehicle {
    private int numSeats;
    private int numDoors;

    public Car(Type type, String matricula, float price, String marca, String descripcion, Color color, int bateria, State estado, String carnet, Date date, int numSeats, int numDoors) {
        super(type, matricula, price, marca, descripcion, color, bateria, estado, carnet, date);
        this.numSeats = numSeats;
        this.numDoors = numDoors;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    @Override
    public String toString() {
        return super.toString() + "Car{" +
                "numSeats=" + numSeats +
                ", numDoors=" + numDoors +
                '}';
    }
}
