package es.ieslavereda.taserfan.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Vehicle implements Serializable {
    private String matricula;
    private float price;
    private String marca;
    private String descripcion;
    private Color color;
    private int bateria;
    private State estado;
    private String carnet;
    private Type type;
    private Date date;

    public Vehicle(Type type, String matricula, float price, String marca, String descripcion, Color color, int bateria, State estado, String carnet, Date date) {
        this.type = type;
        this.matricula = matricula;
        this.price = price;
        this.marca = marca;
        this.descripcion = descripcion;
        this.color = color;
        this.bateria = bateria;
        this.estado = estado;
        this.carnet = carnet;
        this.date = date;
    }

    public Vehicle(Type type, String matricula, float price, String marca, Color color, State estado) {
        this.type = type;
        this.matricula = matricula;
        this.price = price;
        this.marca = marca;
        this.color = color;
        this.estado = estado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public State getEstado() {
        return estado;
    }

    public void setEstado(State estado) {
        this.estado = estado;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "matricula='" + matricula + '\'' +
                ", price=" + price +
                ", marca='" + marca + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", color=" + color +
                ", bateria=" + bateria +
                ", estado=" + estado +
                ", carnet='" + carnet + '\'' +
                ", type=" + type +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vehicle) {
            Vehicle vehicle = (Vehicle) o;
            return matricula.equals(vehicle.matricula);
        } else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
}
