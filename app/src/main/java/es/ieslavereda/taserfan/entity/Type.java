package es.ieslavereda.taserfan.entity;

public enum Type {
    COCHE("COCHE"), MOTO("MOTO"), BICICLETA("BICICLETA"), PATINETE("PATINETE");

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
