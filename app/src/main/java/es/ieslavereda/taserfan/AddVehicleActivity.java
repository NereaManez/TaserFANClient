package es.ieslavereda.taserfan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.sql.Date;
import java.util.Calendar;

import es.ieslavereda.taserfan.api.Connector;
import es.ieslavereda.taserfan.api.Result;
import es.ieslavereda.taserfan.base.BaseActivity;
import es.ieslavereda.taserfan.base.CallInterface;
import es.ieslavereda.taserfan.entity.Bike;
import es.ieslavereda.taserfan.entity.Car;
import es.ieslavereda.taserfan.entity.Color;
import es.ieslavereda.taserfan.entity.Motorbike;
import es.ieslavereda.taserfan.entity.Scooter;
import es.ieslavereda.taserfan.entity.State;
import es.ieslavereda.taserfan.entity.Type;

public class AddVehicleActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, CallInterface {

    EditText matriculaEdit;
    EditText marcaEdit;
    EditText bateriaEdit;
    EditText numPuertasEdit;
    EditText numPlazasEdit;
    EditText velmaxEdit;
    EditText cilindradaEdit;
    EditText tipoEdit;
    EditText numRuedasEdit;
    EditText tamanyoEdit;
    EditText precioEdit;

    Button add;

    Spinner spinnerTipo;
    Spinner spinnerColor;
    Spinner spinnerCarnet;
    Spinner spinnerEstado;

    ArrayAdapter<String> tipoAA;
    String[] aTipo = {"COCHE", "MOTO", "BICICLETA", "PATINETE"};
    ArrayAdapter<String> colorAA;
    String[] aColores = {"verde", "rojo", "amarillo", "azul", "negro", "blanco"};
    ArrayAdapter<String> carnetAA;
    String[] aCarnet = {"A", "B", "C", "D", "E", "F"};
    ArrayAdapter<String> estadoAA;
    String[] aEstado = { "baja", "alquilado", "taller", "preparado", "reserverdo"};

    Context context;

    Car c;
    Motorbike m;
    Bike b;
    Scooter s;

    Color color;
    State state;

    ConstraintLayout lc;
    ConstraintLayout lm;
    ConstraintLayout lb;
    ConstraintLayout ls;

    Result r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        context = this;

        matriculaEdit = findViewById(R.id.matricula);
        marcaEdit = findViewById(R.id.marcaAdd);
        bateriaEdit = findViewById(R.id.bateriaAdd);
        numPuertasEdit = findViewById(R.id.puertasAdd);
        numPlazasEdit = findViewById(R.id.plazasAdd);
        velmaxEdit = findViewById(R.id.velmaxAdd);
        cilindradaEdit = findViewById(R.id.cilindradaAdd);
        tipoEdit = findViewById(R.id.tipoAdd);
        numRuedasEdit = findViewById(R.id.numruedasAdd);
        tamanyoEdit = findViewById(R.id.tamanyoAdd);
        precioEdit = findViewById(R.id.precioAdd);

        lc = findViewById(R.id.lcAdd);
        lm = findViewById(R.id.lmAdd);
        lb = findViewById(R.id.lbAdd);
        ls = findViewById(R.id.lsAdd);

        add = findViewById(R.id.addokbtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!matriculaEdit.getText().toString().isEmpty() && !marcaEdit.getText().toString().isEmpty() && !bateriaEdit.getText().toString().isEmpty()) {
                    switch (spinnerColor.getSelectedItem().toString()) {
                        case "verde":
                            color = Color.VERDE;
                            break;
                        case "rojo":
                            color = Color.ROJO;
                            break;
                        case "azul":
                            color = Color.AZUL;
                            break;
                        case "amarillo":
                            color = Color.AMARILLO;
                            break;
                        case "blanco":
                            color = Color.BLANCO;
                            break;
                        case "negro":
                            color = Color.NEGRO;
                            break;
                    }

                    switch (spinnerEstado.getSelectedItem().toString()){
                        case "baja":
                            state = State.BAJA;
                            break;
                        case "alquilado":
                            state = State.ALQUILADO;
                            break;
                        case "taller":
                            state = State.TALLER;
                            break;
                        case "preparado":
                            state = State.PREPARADO;
                            break;
                        case "reservado":
                            state = State.RESERVADO;
                            break;
                    }

                    switch (spinnerTipo.getSelectedItem().toString()) {
                        case "COCHE":
                            if (!numPuertasEdit.getText().toString().isEmpty() && !numPlazasEdit.getText().toString().isEmpty()) {
                                c = new Car(Type.COCHE, matriculaEdit.getText().toString(), Float.parseFloat(precioEdit.getText().toString()), marcaEdit.getText().toString(), "", color, Integer.parseInt(bateriaEdit.getText().toString()), state, spinnerCarnet.getSelectedItem().toString(), new Date(Calendar.getInstance().getTime().getTime()), Integer.parseInt(numPuertasEdit.getText().toString()), Integer.parseInt(numPlazasEdit.getText().toString()));
                                executeCall(AddVehicleActivity.this);
                            }
                            break;
                        case "MOTO":
                            if (!velmaxEdit.getText().toString().isEmpty() && !cilindradaEdit.getText().toString().isEmpty()) {
                                m = new Motorbike(Type.MOTO, matriculaEdit.getText().toString(), Float.parseFloat(precioEdit.getText().toString()), marcaEdit.getText().toString(), "", color, Integer.parseInt(bateriaEdit.getText().toString()), state, spinnerCarnet.getSelectedItem().toString(), new Date(Calendar.getInstance().getTime().getTime()), Integer.parseInt(velmaxEdit.getText().toString()), Integer.parseInt(cilindradaEdit.getText().toString()));
                                executeCall(AddVehicleActivity.this);
                            }
                            break;
                        case "BICICLETA":
                            if (!tipoEdit.getText().toString().isEmpty()) {
                                b = new Bike(Type.BICICLETA, matriculaEdit.getText().toString(), Float.parseFloat(precioEdit.getText().toString()), marcaEdit.getText().toString(), "", color, Integer.parseInt(bateriaEdit.getText().toString()), state, new Date(Calendar.getInstance().getTime().getTime()), spinnerCarnet.getSelectedItem().toString(), tipoEdit.getText().toString());
                                executeCall(AddVehicleActivity.this);
                            }
                            break;
                        case "PATINETE":
                            if (!numRuedasEdit.getText().toString().isEmpty()) {
                                s = new Scooter(Type.PATINETE, matriculaEdit.getText().toString(), Float.parseFloat(precioEdit.getText().toString()), marcaEdit.getText().toString(), "", color, Integer.parseInt(bateriaEdit.getText().toString()), state, spinnerCarnet.getSelectedItem().toString(), new Date(Calendar.getInstance().getTime().getTime()), Integer.parseInt(numRuedasEdit.getText().toString()), Float.parseFloat(tamanyoEdit.getText().toString()));
                                executeCall(AddVehicleActivity.this);
                            }
                            break;
                        default:
                            Toast.makeText(AddVehicleActivity.this, "Tipo no valido", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        spinnerTipo = findViewById(R.id.spinnerTipo);
        spinnerTipo.setOnItemSelectedListener(this);
        spinnerColor = findViewById(R.id.colorSpinner);
        spinnerCarnet = findViewById(R.id.carnetSpinner);
        spinnerEstado = findViewById(R.id.estadoSpinner);

        tipoAA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, aTipo);
        tipoAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        colorAA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, aColores);
        colorAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carnetAA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, aCarnet);
        carnetAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoAA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, aEstado);
        estadoAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTipo.setAdapter(tipoAA);
        spinnerColor.setAdapter(colorAA);
        spinnerCarnet.setAdapter(carnetAA);
        spinnerEstado.setAdapter(estadoAA);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getSelectedItem().toString();
        switch (selected){
            case "COCHE":
                lc.setVisibility(View.VISIBLE);
                lm.setVisibility(View.GONE);
                lb.setVisibility(View.GONE);
                ls.setVisibility(View.GONE);
                break;
            case "MOTO":
                lc.setVisibility(View.GONE);
                lm.setVisibility(View.VISIBLE);
                lb.setVisibility(View.GONE);
                ls.setVisibility(View.GONE);
                break;
            case "BICICLETA":
                lc.setVisibility(View.GONE);
                lm.setVisibility(View.GONE);
                lb.setVisibility(View.VISIBLE);
                ls.setVisibility(View.GONE);
                break;
            case "PATINETE":
                lc.setVisibility(View.GONE);
                lm.setVisibility(View.GONE);
                lb.setVisibility(View.GONE);
                ls.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void doInBackground() {
        if (c != null)
            r = Connector.getConector().post(Car.class, c, "/car");
        else if (m != null)
            r = Connector.getConector().post(Motorbike.class, c, "/moto");
        else if (b != null)
            r = Connector.getConector().post(Bike.class, c, "/bike");
        else if (s != null)
            r = Connector.getConector().post(Scooter.class, c, "/scooter");
    }

    @Override
    public void doInUI() {
        if (r instanceof Result.Success)
            Toast.makeText(AddVehicleActivity.this, "Vehiculo insertado", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(AddVehicleActivity.this, "Fallo al insertar vehiculo", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(AddVehicleActivity.this, ViewVehicleActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("matricula", matriculaEdit.getText().toString());
        outState.putString("marca", marcaEdit.getText().toString());
        outState.putString("bateria", bateriaEdit.getText().toString());
        outState.putString("puertas", numPuertasEdit.getText().toString());
        outState.putString("plazas", numPlazasEdit.getText().toString());
        outState.putString("velmax", velmaxEdit.getText().toString());
        outState.putString("cilindrada", cilindradaEdit.getText().toString());
        outState.putString("tipo", tipoEdit.getText().toString());
        outState.putString("ruedas", numRuedasEdit.getText().toString());
        outState.putString("tamanyo", tamanyoEdit.getText().toString());
        outState.putString("precio", precioEdit.getText().toString());

        outState.putInt("sTipo", spinnerTipo.getSelectedItemPosition());
        outState.putInt("sColor", spinnerColor.getSelectedItemPosition());
        outState.putInt("sCarnet", spinnerCarnet.getSelectedItemPosition());
        outState.putInt("sEstado", spinnerEstado.getSelectedItemPosition());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        matriculaEdit.setText(savedInstanceState.getString("matricula"));
        marcaEdit.setText(savedInstanceState.getString("marca"));
        bateriaEdit.setText(savedInstanceState.getString("bateria"));
        numPlazasEdit.setText(savedInstanceState.getString("plazas"));
        numPuertasEdit.setText(savedInstanceState.getString("puertas"));
        velmaxEdit.setText(savedInstanceState.getString("velmax"));
        cilindradaEdit.setText(savedInstanceState.getString("cilindrada"));
        tipoEdit.setText(savedInstanceState.getString("tipo"));
        numRuedasEdit.setText(savedInstanceState.getString("ruedas"));
        tamanyoEdit.setText(savedInstanceState.getString("tamanyo"));
        precioEdit.setText(savedInstanceState.getString("precio"));

        spinnerTipo.setSelection(savedInstanceState.getInt("sTipo"));
        spinnerColor.setSelection(savedInstanceState.getInt("sColor"));
        spinnerCarnet.setSelection(savedInstanceState.getInt("sCarnet"));
        spinnerEstado.setSelection(savedInstanceState.getInt("sEstado"));
    }
}