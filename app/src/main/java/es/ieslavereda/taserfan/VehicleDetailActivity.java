package es.ieslavereda.taserfan;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

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
import es.ieslavereda.taserfan.entity.Vehicle;

public class VehicleDetailActivity extends BaseActivity implements CallInterface {

    List<Vehicle> vehicles;
    int index;
    Vehicle vehicle = null;

    Result<Car> rc;
    Car c;
    Car cUpdate;
    Result<Motorbike> rm;
    Motorbike m;
    Motorbike mUpdate;
    Result<Bike> rb;
    Bike b;
    Bike bUpdate;
    Result<Scooter> rs;
    Scooter s;
    Scooter sUpdate;

    ImageView vehicleImg;
    TextView matricula;
    TextView marcaText;
    EditText marcaEt;
    TextView colorText;
    TextView carnetText;
    TextView bateriaText;
    EditText bateriaEt;
    TextView estadoText;
    TextView fechaText;
    TextView precio;
    EditText precioEt;
    ImageView pre;
    ImageView pos;

    Spinner spinnerColor;
    Spinner spinnerCarnet;
    Spinner spinnerEstado;

    ArrayAdapter<String> colorAA;
    String[] aColores = {"verde", "rojo", "amarillo", "azul", "negro", "blanco"};
    ArrayAdapter<String> carnetAA;
    String[] aCarnet = {"AM", "A", "B", "C", "D", "E", "F"};
    ArrayAdapter<String> estadoAA;
    String[] aEstado = { "baja", "alquilado", "taller", "preparado", "reserverdo"};

    TextView numPuertas;
    EditText numPuertasEt;
    TextView numPlazas;
    EditText numPlazasEt;
    TextView velmax;
    EditText velmaxEt;
    TextView cilindrada;
    EditText cilindradaEt;
    TextView tipo;
    EditText tipoEt;
    TextView numRuedas;
    EditText numRuedasEt;
    TextView tamanyo;
    EditText tamanyoEt;

    ConstraintLayout lc;
    ConstraintLayout lm;
    ConstraintLayout lb;
    ConstraintLayout ls;

    Button update;
    Button confirmUpdate;

    Result r = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        index = getIntent().getIntExtra("index", 0);
        vehicles = (List<Vehicle>) getIntent().getSerializableExtra("list");

        vehicleImg = findViewById(R.id.vehicleImg);
        matricula = findViewById(R.id.matricula);
        marcaText = findViewById(R.id.marcaText);
        colorText = findViewById(R.id.colorText);
        carnetText = findViewById(R.id.carnetText);
        bateriaText = findViewById(R.id.bateriaText);
        estadoText = findViewById(R.id.estadoText);
        fechaText = findViewById(R.id.fechaText);
        precio = findViewById(R.id.precio);

        spinnerColor = findViewById(R.id.colorSpinner);
        spinnerCarnet = findViewById(R.id.carnetSpinner);
        spinnerEstado = findViewById(R.id.estadoSpinner);

        colorAA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, aColores);
        colorAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carnetAA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, aCarnet);
        carnetAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoAA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, aEstado);
        estadoAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerColor.setAdapter(colorAA);
        spinnerCarnet.setAdapter(carnetAA);
        spinnerEstado.setAdapter(estadoAA);

        marcaEt = findViewById(R.id.marcaEditTtext);
        bateriaEt = findViewById(R.id.bateriaEditTtext);
        precioEt = findViewById(R.id.precioEditTtext);

        lc = findViewById(R.id.lc);
        numPuertas = findViewById(R.id.puertasText);
        numPlazas = findViewById(R.id.plazasText);

        numPuertasEt = findViewById(R.id.puertasEditTtext);
        numPlazasEt = findViewById(R.id.plazasEditText);

        lm = findViewById(R.id.lm);
        velmax = findViewById(R.id.velmaxText);
        cilindrada = findViewById(R.id.cilindradaText);

        velmaxEt = findViewById(R.id.velmaxEditTtext);
        cilindradaEt = findViewById(R.id.cilindradaEditTtext);

        lb = findViewById(R.id.lb);
        tipo = findViewById(R.id.tipoText);

        tipoEt = findViewById(R.id.tipoEditTtext);

        ls = findViewById(R.id.ls);
        numRuedas = findViewById(R.id.numruedasText);
        tamanyo = findViewById(R.id.tamanyoText);

        numRuedasEt = findViewById(R.id.numruedasEditTtext);
        tamanyoEt = findViewById(R.id.tamanyoEditTtext);

        update = findViewById(R.id.updatebtn);
        confirmUpdate = findViewById(R.id.updateokbtn);
        pre = findViewById(R.id.pre);
        pos = findViewById(R.id.post);

        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    index--;

                    executeCall(VehicleDetailActivity.this);
                }
            }
        });

        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < vehicles.size() - 1) {
                    index++;

                    executeCall(VehicleDetailActivity.this);
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pre.setClickable(false);
                pos.setClickable(false);

                marcaEt.setText(marcaText.getText());
                marcaEt.setVisibility(View.VISIBLE);
                marcaText.setVisibility(View.GONE);

                spinnerColor.setVisibility(View.VISIBLE);
                switch (vehicle.getColor()) {
                    case VERDE:
                        spinnerColor.setSelection(0);
                        break;
                    case ROJO:
                        spinnerColor.setSelection(1);
                        break;
                    case AMARILLO:
                        spinnerColor.setSelection(2);
                        break;
                    case AZUL:
                        spinnerColor.setSelection(3);
                        break;
                    case NEGRO:
                        spinnerColor.setSelection(4);
                        break;
                    default:
                        spinnerColor.setSelection(5);
                }
                colorText.setVisibility(View.GONE);

                spinnerEstado.setVisibility(View.VISIBLE);
                switch (vehicle.getEstado()) {
                    case BAJA:
                        spinnerEstado.setSelection(0);
                        break;
                    case ALQUILADO:
                        spinnerEstado.setSelection(1);
                        break;
                    case TALLER:
                        spinnerEstado.setSelection(2);
                        break;
                    case PREPARADO:
                        spinnerEstado.setSelection(3);
                        break;
                    default:
                        spinnerEstado.setSelection(4);
                }
                estadoText.setVisibility(View.GONE);

                bateriaEt.setText(bateriaText.getText());
                bateriaEt.setVisibility(View.VISIBLE);
                bateriaText.setVisibility(View.GONE);

                precioEt.setText(precio.getText().toString().substring(0, precio.getText().toString().length() - 1));
                precioEt.setVisibility(View.VISIBLE);
                precio.setVisibility(View.GONE);

                switch (vehicle.getType()) {
                    case COCHE:
                        numPuertasEt.setText(numPuertas.getText());
                        numPuertasEt.setVisibility(View.VISIBLE);
                        numPuertas.setVisibility(View.GONE);

                        numPlazasEt.setText(numPlazas.getText());
                        numPlazasEt.setVisibility(View.VISIBLE);
                        numPlazas.setVisibility(View.GONE);

                        spinnerCarnet.setVisibility(View.VISIBLE);
                        switch (c.getCarnet()) {
                            case "AM":
                                spinnerCarnet.setSelection(0);
                                break;
                            case "A":
                                spinnerCarnet.setSelection(1);
                                break;
                            case "B":
                                spinnerCarnet.setSelection(2);
                                break;
                            case "C":
                                spinnerCarnet.setSelection(3);
                                break;
                            case "D":
                                spinnerCarnet.setSelection(4);
                                break;
                            case "E":
                                spinnerCarnet.setSelection(5);
                                break;
                            default:
                                spinnerCarnet.setSelection(6);
                        }
                        carnetText.setVisibility(View.GONE);
                        break;
                    case MOTO:
                        velmaxEt.setText(velmax.getText());
                        velmaxEt.setVisibility(View.VISIBLE);
                        velmax.setVisibility(View.GONE);

                        cilindradaEt.setText(cilindrada.getText());
                        cilindradaEt.setVisibility(View.VISIBLE);
                        cilindrada.setVisibility(View.GONE);

                        spinnerCarnet.setVisibility(View.VISIBLE);
                        switch (m.getCarnet()) {
                            case "AM":
                                spinnerCarnet.setSelection(0);
                                break;
                            case "A":
                                spinnerCarnet.setSelection(1);
                                break;
                            case "B":
                                spinnerCarnet.setSelection(2);
                                break;
                            case "C":
                                spinnerCarnet.setSelection(3);
                                break;
                            case "D":
                                spinnerCarnet.setSelection(4);
                                break;
                            case "E":
                                spinnerCarnet.setSelection(5);
                                break;
                            default:
                                spinnerCarnet.setSelection(6);
                        }
                        carnetText.setVisibility(View.GONE);
                        break;
                    case BICICLETA:
                        tipoEt.setText(tipo.getText());
                        tipoEt.setVisibility(View.VISIBLE);
                        tipo.setVisibility(View.GONE);

                        spinnerCarnet.setVisibility(View.VISIBLE);
                        switch (b.getCarnet()) {
                            case "AM":
                                spinnerCarnet.setSelection(0);
                                break;
                            case "A":
                                spinnerCarnet.setSelection(1);
                                break;
                            case "B":
                                spinnerCarnet.setSelection(2);
                                break;
                            case "C":
                                spinnerCarnet.setSelection(3);
                                break;
                            case "D":
                                spinnerCarnet.setSelection(4);
                                break;
                            case "E":
                                spinnerCarnet.setSelection(5);
                                break;
                            default:
                                spinnerCarnet.setSelection(6);
                        }
                        carnetText.setVisibility(View.GONE);
                        break;
                    case PATINETE:
                        numRuedasEt.setText(numRuedas.getText());
                        numRuedasEt.setVisibility(View.VISIBLE);
                        numRuedas.setVisibility(View.GONE);

                        tamanyoEt.setText(tamanyo.getText());
                        tamanyoEt.setVisibility(View.VISIBLE);
                        tamanyo.setVisibility(View.GONE);

                        spinnerCarnet.setVisibility(View.VISIBLE);
                        switch (s.getCarnet()) {
                            case "AM":
                                spinnerCarnet.setSelection(0);
                                break;
                            case "A":
                                spinnerCarnet.setSelection(1);
                                break;
                            case "B":
                                spinnerCarnet.setSelection(2);
                                break;
                            case "C":
                                spinnerCarnet.setSelection(3);
                                break;
                            case "D":
                                spinnerCarnet.setSelection(4);
                                break;
                            case "E":
                                spinnerCarnet.setSelection(5);
                                break;
                            default:
                                spinnerCarnet.setSelection(6);
                        }
                        carnetText.setVisibility(View.GONE);
                        break;
                }

                update.setVisibility(View.GONE);
                confirmUpdate.setVisibility(View.VISIBLE);
            }
        });

        confirmUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!marcaEt.getText().toString().isEmpty() && !bateriaEt.getText().toString().isEmpty()) {

                    switch (vehicle.getType()) {
                        case COCHE:
                            if (!numPuertasEt.getText().toString().isEmpty() && !numPlazasEt.getText().toString().isEmpty()) {
                                cUpdate = new Car(vehicle.getType(), vehicle.getMatricula(), Float.parseFloat(precioEt.getText().toString()), marcaEt.getText().toString(), vehicle.getDescripcion(), Color.valueOf(spinnerColor.getSelectedItem().toString()), Integer.parseInt(bateriaEt.getText().toString()), State.valueOf(spinnerEstado.getSelectedItem().toString()), spinnerCarnet.getSelectedItem().toString(), c.getDate(), Integer.parseInt(numPlazasEt.getText().toString()), Integer.parseInt(numPuertasEt.getText().toString()));
                            }
                            break;
                        case MOTO:
                            if (!velmaxEt.getText().toString().isEmpty() && !cilindradaEt.getText().toString().isEmpty()) {
                                mUpdate = new Motorbike(vehicle.getType(), vehicle.getMatricula(), Float.parseFloat(precioEt.getText().toString()), marcaEt.getText().toString(), vehicle.getDescripcion(), Color.valueOf(spinnerColor.getSelectedItem().toString()), Integer.parseInt(bateriaEt.getText().toString()), State.valueOf(spinnerEstado.getSelectedItem().toString()), spinnerCarnet.getSelectedItem().toString(), m.getDate(), Integer.parseInt(velmaxEt.getText().toString()), Integer.parseInt(cilindradaEt.getText().toString()));
                            }
                            break;
                        case BICICLETA:
                            if (!tipoEt.getText().toString().isEmpty()) {
                                bUpdate = new Bike(vehicle.getType(), vehicle.getMatricula(), Float.parseFloat(precioEt.getText().toString()), marcaEt.getText().toString(), vehicle.getDescripcion(), Color.valueOf(spinnerColor.getSelectedItem().toString()), Integer.parseInt(bateriaEt.getText().toString()), State.valueOf(spinnerEstado.getSelectedItem().toString()), b.getDate(), spinnerCarnet.getSelectedItem().toString(), tipoEt.getText().toString());
                            }
                            break;
                        case PATINETE:
                            if (!numRuedasEt.getText().toString().isEmpty()) {
                                sUpdate = new Scooter(vehicle.getType(), vehicle.getMatricula(), Float.parseFloat(precioEt.getText().toString()), marcaEt.getText().toString(), vehicle.getDescripcion(), Color.valueOf(spinnerColor.getSelectedItem().toString()), Integer.parseInt(bateriaEt.getText().toString()), State.valueOf(spinnerEstado.getSelectedItem().toString()), spinnerCarnet.getSelectedItem().toString(), s.getDate(), Integer.parseInt(numRuedasEt.getText().toString()), Float.parseFloat(tamanyoEt.getText().toString()));
                            }
                            break;
                    }

                    executeCall(new CallInterface() {
                        @Override
                        public void doInBackground() {
                            switch (vehicle.getType()) {
                                case COCHE:
                                    r = Connector.getConector().put(Car.class, cUpdate, "/car");
                                    break;
                                case MOTO:
                                    r = Connector.getConector().put(Motorbike.class, mUpdate, "/moto");
                                    break;
                                case BICICLETA:
                                    r = Connector.getConector().put(Bike.class, bUpdate, "/bike");
                                    break;
                                case PATINETE:
                                    r = Connector.getConector().put(Scooter.class, sUpdate, "/scooter");
                                    break;
                            }

                        }

                        @Override
                        public void doInUI() {
                            if (r instanceof Result.Success) {
                                marcaText.setText(marcaEt.getText());
                                marcaText.setVisibility(View.VISIBLE);
                                marcaEt.setVisibility(View.GONE);

                                bateriaText.setText(bateriaEt.getText());
                                bateriaText.setVisibility(View.VISIBLE);
                                bateriaEt.setVisibility(View.GONE);

                                precio.setText(precioEt.getText().toString() + "€");
                                precio.setVisibility(View.VISIBLE);
                                precioEt.setVisibility(View.GONE);

                                colorText.setText(spinnerColor.getSelectedItem().toString());
                                colorText.setVisibility(View.VISIBLE);
                                spinnerColor.setVisibility(View.GONE);

                                estadoText.setText(spinnerEstado.getSelectedItem().toString());
                                estadoText.setVisibility(View.VISIBLE);
                                spinnerEstado.setVisibility(View.GONE);

                                carnetText.setText(spinnerCarnet.getSelectedItem().toString());
                                carnetText.setVisibility(View.VISIBLE);
                                spinnerCarnet.setVisibility(View.GONE);

                                switch (vehicle.getType()) {
                                    case COCHE:
                                        if (!numPuertasEt.getText().toString().isEmpty() && !numPlazasEt.getText().toString().isEmpty()) {
                                            numPuertas.setText(numPuertasEt.getText());
                                            numPuertas.setVisibility(View.VISIBLE);
                                            numPuertasEt.setVisibility(View.GONE);

                                            numPlazas.setText(numPlazasEt.getText());
                                            numPlazas.setVisibility(View.VISIBLE);
                                            numPlazasEt.setVisibility(View.GONE);
                                        }
                                        break;
                                    case MOTO:
                                        if (!velmaxEt.getText().toString().isEmpty() && !cilindradaEt.getText().toString().isEmpty()) {
                                            velmax.setText(velmaxEt.getText());
                                            velmax.setVisibility(View.VISIBLE);
                                            velmaxEt.setVisibility(View.GONE);

                                            cilindrada.setText(cilindradaEt.getText());
                                            cilindrada.setVisibility(View.VISIBLE);
                                            cilindradaEt.setVisibility(View.GONE);
                                        }
                                        break;
                                    case BICICLETA:
                                        if (!tipoEt.getText().toString().isEmpty()) {
                                            tipo.setText(tipoEt.getText());
                                            tipo.setVisibility(View.VISIBLE);
                                            tipoEt.setVisibility(View.GONE);
                                        }
                                        break;
                                    case PATINETE:
                                        if (!numRuedasEt.getText().toString().isEmpty()) {
                                            numRuedas.setText(numRuedasEt.getText());
                                            numRuedas.setVisibility(View.VISIBLE);
                                            numRuedasEt.setVisibility(View.GONE);

                                            tamanyo.setText(tamanyoEt.getText());
                                            tamanyo.setVisibility(View.VISIBLE);
                                            tamanyoEt.setVisibility(View.GONE);
                                        }
                                        break;
                                }

                                update.setVisibility(View.VISIBLE);
                                confirmUpdate.setVisibility(View.GONE);

                                pre.setClickable(true);
                                pos.setClickable(true);
                            } else {
                                Toast.makeText(VehicleDetailActivity.this, "No se ha podido hacer update", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(VehicleDetailActivity.this, "Hay campos vacios", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        executeCall(this);
    }

    @Override
    public void doInBackground() {
        vehicle = vehicles.get(index);
        try {
            switch (vehicle.getType()) {
                case COCHE:
                    rc = Connector.getConector().get(Car.class, "/car?matricula=" + vehicle.getMatricula());
                    break;
                case MOTO:
                    rm = Connector.getConector().get(Motorbike.class, "/moto?matricula=" + vehicle.getMatricula());
                    break;
                case BICICLETA:
                    rb = Connector.getConector().get(Bike.class, "/bike?matricula=" + vehicle.getMatricula());
                    break;
                case PATINETE:
                    rs = Connector.getConector().get(Scooter.class, "/scooter?matricula=" + vehicle.getMatricula());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doInUI() {
        switch (vehicle.getType()) {
            case COCHE:
                c = ((Result.Success<Car>) rc).getData();
                lc.setVisibility(View.VISIBLE);
                lm.setVisibility(View.GONE);
                lb.setVisibility(View.GONE);
                ls.setVisibility(View.GONE);
                vehicleImg.setImageResource(R.mipmap.ic_coche_foreground);
                numPuertas.setText(String.valueOf(c.getNumDoors()));
                numPlazas.setText(String.valueOf(c.getNumSeats()));

                carnetText.setText(c.getCarnet());
                bateriaText.setText(String.valueOf(c.getBateria()));
                if (c.getDate() != null)
                    fechaText.setText(c.getDate().toString());
                else
                    fechaText.setText("no definida");
                break;
            case MOTO:
                m = ((Result.Success<Motorbike>) rm).getData();
                lc.setVisibility(View.GONE);
                lm.setVisibility(View.VISIBLE);
                lb.setVisibility(View.GONE);
                ls.setVisibility(View.GONE);
                vehicleImg.setImageResource(R.mipmap.ic_moto_foreground);
                velmax.setText(String.valueOf(m.getMaxVelocity()));
                cilindrada.setText(String.valueOf(m.getDisplacement()));

                carnetText.setText(m.getCarnet());
                bateriaText.setText(String.valueOf(m.getBateria()));
                if (m.getDate() != null)
                    fechaText.setText(m.getDate().toString());
                else
                    fechaText.setText("no definida");
                break;
            case BICICLETA:
                b = ((Result.Success<Bike>) rb).getData();
                lc.setVisibility(View.GONE);
                lm.setVisibility(View.GONE);
                lb.setVisibility(View.VISIBLE);
                ls.setVisibility(View.GONE);
                vehicleImg.setImageResource(R.mipmap.ic_bicicleta_foreground);
                tipo.setText(b.getTipo());

                carnetText.setText(b.getCarnet());
                bateriaText.setText(String.valueOf(b.getBateria()));
                if (b.getDate() != null)
                    fechaText.setText(b.getDate().toString());
                else
                    fechaText.setText("no definida");
                break;
            case PATINETE:
                s = ((Result.Success<Scooter>) rs).getData();
                lc.setVisibility(View.GONE);
                lm.setVisibility(View.GONE);
                lb.setVisibility(View.GONE);
                ls.setVisibility(View.VISIBLE);
                vehicleImg.setImageResource(R.mipmap.ic_patinete_foreground);
                numRuedas.setText(String.valueOf(s.getNumWheels()));
                tamanyo.setText(String.valueOf(s.getSize()));

                carnetText.setText(s.getCarnet());
                bateriaText.setText(String.valueOf(s.getBateria()));
                if (s.getDate() != null)
                    fechaText.setText(s.getDate().toString());
                else
                    fechaText.setText("no definida");
                break;
        }

        matricula.setText(vehicle.getMatricula());
        marcaText.setText(vehicle.getMarca());
        colorText.setText(vehicle.getColor().getColor());
        estadoText.setText(vehicle.getEstado().getstate());
        precio.setText(vehicle.getPrice() + "€");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list", (Serializable) vehicles);
        outState.putInt("index", index);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        vehicles = (List<Vehicle>) savedInstanceState.getSerializable("list");
        index = savedInstanceState.getInt("index");

        if (vehicles != null)
            doInUI();
    }
}