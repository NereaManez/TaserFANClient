package es.ieslavereda.taserfan.entity;

import java.sql.Date;

public class Motorbike extends Vehicle {
    private float maxVelocity;
    private float displacement;

    public Motorbike(Type type, String matricula, float price, String marca, String descripcion, Color color, int bateria, State estado, String carnet, Date date, float maxVelocity, float displacement) {
        super(type, matricula, price, marca, descripcion, color, bateria, estado, carnet, date);
        this.maxVelocity = maxVelocity;
        this.displacement = displacement;
    }

    public float getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(float maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public float getDisplacement() {
        return displacement;
    }

    public void setDisplacement(float displacement) {
        this.displacement = displacement;
    }

    @Override
    public String toString() {
        return super.toString() + "Motorbike{" +
                "maxVelocity=" + maxVelocity +
                ", displacement=" + displacement +
                '}';
    }
}
