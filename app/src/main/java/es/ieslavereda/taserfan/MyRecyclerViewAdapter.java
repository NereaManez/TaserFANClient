package es.ieslavereda.taserfan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;

import java.io.Serializable;
import java.util.List;

import es.ieslavereda.taserfan.entity.Vehicle;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> implements Serializable {
    private LayoutInflater inflater;
    private View.OnClickListener onClickListener;
    private Context context;
    private List<Vehicle> vehicles;

    public MyRecyclerViewAdapter(Context context, List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.view_vehicle_element, parent, false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vehicle v = vehicles.get(position);

        switch (v.getType().getType()) {
            case "COCHE":
                holder.vehicleImg.setImageResource(R.mipmap.ic_coche_foreground);
                break;
            case "MOTO":
                holder.vehicleImg.setImageResource(R.mipmap.ic_moto_foreground);
                break;
            case "BICICLETA":
                holder.vehicleImg.setImageResource(R.mipmap.ic_bicicleta_foreground);
                break;
            case "PATINETE":
                holder.vehicleImg.setImageResource(R.mipmap.ic_patinete_foreground);
        }

        holder.state.setText(v.getEstado().getstate());
        switch (v.getEstado().getstate()) {
            case "preparado":
                holder.state.setBackgroundResource(R.color.green);
                break;
            case "alquilado":
                holder.state.setBackgroundResource(R.color.blue);
                break;
            case "reservado":
                holder.state.setBackgroundResource(R.color.orange);
                break;
            case "taller":
                holder.state.setBackgroundResource(R.color.gray);
                break;
            case "baja":
                holder.state.setBackgroundResource(R.color.red);
                break;
            default:
                holder.state.setBackgroundResource(R.color.black);
        }

        holder.registration.setText(v.getMatricula());
        holder.brand.setText(v.getMarca());
        holder.color.setText(v.getColor().getColor());
        holder.price.setText(v.getPrice() + "€");
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public static class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        ImageView vehicleImg;
        TextView state;
        TextView registration;
        TextView brand;
        TextView color;
        TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            vehicleImg = itemView.findViewById(R.id.vehicle_image);
            state = itemView.findViewById(R.id.state);
            registration = itemView.findViewById(R.id.registration);
            brand = itemView.findViewById(R.id.brand);
            color = itemView.findViewById(R.id.color);
            price = itemView.findViewById(R.id.price);
        }
    }
}
