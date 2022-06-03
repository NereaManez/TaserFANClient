package es.ieslavereda.taserfan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    Vehicle v;

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
    TextView colorText;
    TextView carnetText;
    TextView bateriaText;
    TextView estadoText;
    TextView fechaText;
    TextView precio;
    ImageView pre;
    ImageView pos;

    TextView numPuertas;
    TextView numPlazas;
    TextView velmax;
    TextView cilindrada;
    TextView tipo;
    TextView numRuedas;
    TextView tamanyo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_detail);

        index = getIntent().getIntExtra("index", 0);
        vehicles = (List<Vehicle>) getIntent().getSerializableExtra("list");

        vehicleImg.findViewById(R.id.vehicleImg);
        matricula.findViewById(R.id.matricula);
        marcaText.findViewById(R.id.marcaText);
        colorText.findViewById(R.id.colorText);
        carnetText.findViewById(R.id.carnetText);
        bateriaText.findViewById(R.id.bateriaText);
        estadoText.findViewById(R.id.estadoText);
        fechaText.findViewById(R.id.fechaText);
        precio.findViewById(R.id.precio);

        numPuertas.findViewById(R.id.puertasText);
        numPlazas.findViewById(R.id.plazasText);

        velmax.findViewById(R.id.velmaxText);
        cilindrada.findViewById(R.id.cilindradaText);

        tipo.findViewById(R.id.tipoText);

        numRuedas.findViewById(R.id.numruedasText);
        tamanyo.findViewById(R.id.tamanyoText);

        pre.findViewById(R.id.pre);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    index--;

                    executeCall(VehicleDetailActivity.this);
                }
            }
        });

        pos.findViewById(R.id.post);
        pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < vehicles.size()-1) {
                    index++;

                    executeCall(VehicleDetailActivity.this);
                }
            }
        });

        executeCall(this);
    }

    @Override
    public void doInBackground() {
        v = vehicles.get(index);

        switch (v.getType()) {
            case COCHE:
                rc = Connector.getConector().get(Car.class, "/car?matricula=" + v.getMatricula());
                break;
            case MOTO:
                rm = Connector.getConector().get(Motorbike.class, "/moto?matricula=" + v.getMatricula());
                break;
            case BICICLETA:
                rb = Connector.getConector().get(Bike.class, "/bike?matricula=" + v.getMatricula());
                break;
            case PATINETE:
                rs = Connector.getConector().get(Scooter.class, "/scooter?matricula=" + v.getMatricula());
                break;
        }
    }

    @Override
    public void doInUI() {
        switch (v.getType()) {
            case COCHE:
                c = ((Result.Success<Car>) rc).getData();
                vehicleImg.setImageResource(R.mipmap.ic_coche);
                numPuertas.setText(String.valueOf(c.getNumDoors()));
                numPlazas.setText(String.valueOf(c.getNumSeats()));
                break;
            case MOTO:
                m = ((Result.Success<Motorbike>) rm).getData();
                vehicleImg.setImageResource(R.mipmap.ic_moto);
                velmax.setText(String.valueOf(m.getMaxVelocity()));
                cilindrada.setText(String.valueOf(m.getDisplacement()));
                break;
            case BICICLETA:
                b = ((Result.Success<Bike>) rb).getData();
                vehicleImg.setImageResource(R.mipmap.ic_bicicleta);
                tipo.setText(b.getTipo());
                break;
            case PATINETE:
                s = ((Result.Success<Scooter>) rs).getData();
                vehicleImg.setImageResource(R.mipmap.ic_patinete);
                numRuedas.setText(String.valueOf(s.getNumWheels()));
                tamanyo.setText(String.valueOf(s.getSize()));
                break;
        }

        matricula.setText(v.getMatricula());
        marcaText.setText(v.getMarca());
        colorText.setText(v.getColor().getColor());
        carnetText.setText(v.getCarnet());
        bateriaText.setText(v.getBateria());
        estadoText.setText(v.getEstado().getstate());
        fechaText.setText(v.getDate().toString());
        precio.setText(v.getPrice() + "€");
    }
}