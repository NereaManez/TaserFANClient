package es.ieslavereda.taserfan.entity;

public enum State {
    PREPARADO("preparado"), ALQUILADO("alquilado"), RESERVADO("reservado"), TALLER("taller"), BAJA("baja");
    
    private String state;
    State(String state) {
        this.state = state;
    }

    public String getstate() {
        return state;
    }
}
