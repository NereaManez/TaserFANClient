package es.ieslavereda.taserfan;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.sql.Date;
import java.util.List;

import es.ieslavereda.taserfan.api.Connector;
import es.ieslavereda.taserfan.api.Result;
import es.ieslavereda.taserfan.base.BaseActivity;
import es.ieslavereda.taserfan.base.CallInterface;
import es.ieslavereda.taserfan.entity.Bike;
import es.ieslavereda.taserfan.entity.Car;
import es.ieslavereda.taserfan.entity.Motorbike;
import es.ieslavereda.taserfan.entity.Scooter;
import es.ieslavereda.taserfan.entity.Vehicle;

public class VehicleDetailActivity extends BaseActivity implements CallInterface {

    List<Vehicle> vehicles;
    int index;
    Vehicle vehicle = null;

    Result<Car> rc;
    Car c;
    Result<Motorbike> rm;
    Motorbike m;
    Result<Bike> rb;
    Bike b;
    Result<Scooter> rs;
    Scooter s;

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
                if (index < vehicles.size()-1) {
                    index++;

                    executeCall(VehicleDetailActivity.this);
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcaEt.setText(marcaText.getText());
                marcaEt.setVisibility(View.VISIBLE);
                marcaText.setVisibility(View.GONE);

                bateriaEt.setText(bateriaText.getText());
                bateriaEt.setVisibility(View.VISIBLE);
                bateriaText.setVisibility(View.GONE);

                precioEt.setText(precio.getText().toString().substring(0, precio.getText().toString().length()-1));
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
                        break;
                    case MOTO:
                        velmaxEt.setText(velmax.getText());
                        velmaxEt.setVisibility(View.VISIBLE);
                        velmax.setVisibility(View.GONE);

                        cilindradaEt.setText(cilindrada.getText());
                        cilindradaEt.setVisibility(View.VISIBLE);
                        cilindrada.setVisibility(View.GONE);
                        break;
                    case BICICLETA:
                        tipoEt.setText(tipo.getText());
                        tipoEt.setVisibility(View.VISIBLE);
                        tipo.setVisibility(View.GONE);
                        break;
                    case PATINETE:
                        numRuedasEt.setText(numRuedas.getText());
                        numRuedasEt.setVisibility(View.VISIBLE);
                        numRuedas.setVisibility(View.GONE);

                        tamanyoEt.setText(tamanyo.getText());
                        tamanyoEt.setVisibility(View.VISIBLE);
                        tamanyo.setVisibility(View.GONE);
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
                                c = new Car(vehicle.getType(), vehicle.getMatricula(), Float.parseFloat(precioEt.getText().toString()), marcaEt.getText().toString(), vehicle.getDescripcion(), vehicle.getColor(), Integer.parseInt(bateriaEt.getText().toString()), vehicle.getEstado(), vehicle.getCarnet(), vehicle.getDate(), Integer.parseInt(numPuertasEt.getText().toString()), Integer.parseInt(numPlazasEt.getText().toString()));
                            }
                            break;
                        case MOTO:
                            if (!velmaxEt.getText().toString().isEmpty() && !cilindradaEt.getText().toString().isEmpty()) {
                                m = new Motorbike(vehicle.getType(), vehicle.getMatricula(), Float.parseFloat(precioEt.getText().toString()), marcaEt.getText().toString(), vehicle.getDescripcion(), vehicle.getColor(), Integer.parseInt(bateriaEt.getText().toString()), vehicle.getEstado(), vehicle.getCarnet(), vehicle.getDate(), Integer.parseInt(velmaxEt.getText().toString()), Integer.parseInt(cilindradaEt.getText().toString()));
                            }
                            break;
                        case BICICLETA:
                            if (!tipoEt.getText().toString().isEmpty()) {
                                b = new Bike(vehicle.getType(), vehicle.getMatricula(), Float.parseFloat(precioEt.getText().toString()), marcaEt.getText().toString(), vehicle.getDescripcion(), vehicle.getColor(), Integer.parseInt(bateriaEt.getText().toString()), vehicle.getEstado(), vehicle.getDate(), vehicle.getCarnet(), tipoEt.getText().toString());
                            }
                            break;
                        case PATINETE:
                            if (!numRuedasEt.getText().toString().isEmpty()) {
                                s = new Scooter(vehicle.getType(), vehicle.getMatricula(), Float.parseFloat(precioEt.getText().toString()), marcaEt.getText().toString(), vehicle.getDescripcion(), vehicle.getColor(), Integer.parseInt(bateriaEt.getText().toString()), vehicle.getEstado(), vehicle.getCarnet(), vehicle.getDate(), Integer.parseInt(numRuedasEt.getText().toString()), Float.parseFloat(tamanyoEt.getText().toString()));
                            }
                            break;
                    }

                    executeCall(new CallInterface() {
                        @Override
                        public void doInBackground() {
                            switch (vehicle.getType()) {
                                case COCHE:
                                    r = Connector.getConector().put(Car.class, c, "/car");
                                    break;
                                case MOTO:
                                    r = Connector.getConector().put(Motorbike.class, m, "/moto");
                                    break;
                                case BICICLETA:
                                    r = Connector.getConector().put(Bike.class, b, "/bike");
                                    break;
                                case PATINETE:
                                    r = Connector.getConector().put(Scooter.class, s, "/scooter");
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

        executeCall(this);
    }

    @Override
    public void doInBackground() {
        vehicle= vehicles.get(index);

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
                vehicleImg.setImageResource(R.mipmap.ic_coche);
                numPuertas.setText(String.valueOf(c.getNumDoors()));
                numPlazas.setText(String.valueOf(c.getNumSeats()));
                break;
            case MOTO:
                m = ((Result.Success<Motorbike>) rm).getData();
                lc.setVisibility(View.GONE);
                lm.setVisibility(View.VISIBLE);
                lb.setVisibility(View.GONE);
                ls.setVisibility(View.GONE);
                vehicleImg.setImageResource(R.mipmap.ic_moto);
                velmax.setText(String.valueOf(m.getMaxVelocity()));
                cilindrada.setText(String.valueOf(m.getDisplacement()));
                break;
            case BICICLETA:
                b = ((Result.Success<Bike>) rb).getData();
                lc.setVisibility(View.GONE);
                lm.setVisibility(View.GONE);
                lb.setVisibility(View.VISIBLE);
                ls.setVisibility(View.GONE);
                vehicleImg.setImageResource(R.mipmap.ic_bicicleta);
                tipo.setText(b.getTipo());
                break;
            case PATINETE:
                s = ((Result.Success<Scooter>) rs).getData();
                lc.setVisibility(View.GONE);
                lm.setVisibility(View.GONE);
                lb.setVisibility(View.GONE);
                ls.setVisibility(View.VISIBLE);
                vehicleImg.setImageResource(R.mipmap.ic_patinete);
                numRuedas.setText(String.valueOf(s.getNumWheels()));
                tamanyo.setText(String.valueOf(s.getSize()));
                break;
        }

        matricula.setText(vehicle.getMatricula());
        marcaText.setText(vehicle.getMarca());
        colorText.setText(vehicle.getColor().getColor());
        carnetText.setText(vehicle.getCarnet());
        bateriaText.setText(vehicle.getBateria());
        estadoText.setText(vehicle.getEstado().getstate());
        fechaText.setText(vehicle.getDate().toString());
        precio.setText(vehicle.getPrice() + "€");
    }
}