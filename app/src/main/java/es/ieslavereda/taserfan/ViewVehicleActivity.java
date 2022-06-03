package es.ieslavereda.taserfan;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.ieslavereda.taserfan.api.Connector;
import es.ieslavereda.taserfan.base.BaseActivity;
import es.ieslavereda.taserfan.base.CallInterface;
import es.ieslavereda.taserfan.entity.Bike;
import es.ieslavereda.taserfan.entity.Car;
import es.ieslavereda.taserfan.entity.Motorbike;
import es.ieslavereda.taserfan.entity.Scooter;
import es.ieslavereda.taserfan.entity.Vehicle;

public class ViewVehicleActivity extends BaseActivity implements CallInterface, View.OnClickListener {

    private RecyclerView recyclerView;
    private List<Vehicle> vehicles;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicle);

        context = this;
        executeCall(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ViewVehicleActivity.this, VehicleDetailActivity.class);

        int index = recyclerView.getChildAdapterPosition(v);
        intent.putExtra("index", index);
        intent.putExtra("list", (Serializable) vehicles);

        startActivity(intent);
    }

    @Override
    public void doInBackground() {
        vehicles = new ArrayList<>(Connector.getConector().getAsList(Vehicle.class, "/vehicles"));
    }

    @Override
    public void doInUI() {
        recyclerView = findViewById(R.id.recyclerView);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, vehicles);
        adapter.setOnClickListener(this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);

        ItemTouchHelper ith = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        // move item in `fromPos` to `toPos` in adapter.
                        recyclerView.getAdapter().notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        return true;// true if moved, false otherwise
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        Vehicle vehicle = vehicles.get(viewHolder.getAdapterPosition());
                        int position = viewHolder.getAdapterPosition();

                        executeCall(new CallInterface() {
                            @Override
                            public void doInBackground() {
                                switch (vehicle.getType()){
                                    case COCHE:
                                        Connector.getConector().delete(Car.class, "/coche?matricula="+ vehicle.getMatricula());
                                        break;
                                    case MOTO:
                                        Connector.getConector().delete(Motorbike.class, "/moto?matricula="+ vehicle.getMatricula());
                                        break;
                                    case BICICLETA:
                                        Connector.getConector().delete(Bike.class, "/bicicleta?matricula="+ vehicle.getMatricula());
                                        break;
                                    case PATINETE:
                                        Connector.getConector().delete(Scooter.class, "/patinete?matricula="+ vehicle.getMatricula());
                                        break;
                                }
                            }

                            @Override
                            public void doInUI() {
                                vehicles.remove(position);
                                adapter.notifyItemRemoved(position);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });

        ith.attachToRecyclerView(recyclerView);
    }
}