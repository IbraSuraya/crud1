package com.fikupn.crud1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fikupn.crud1.R;
import com.fikupn.crud1.database.entitas.Users;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewAdapter>{
    private List<Users> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

//    Constractor
    public UserAdapter(Context context, List<Users> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.fullName.setText(list.get(position).fullName);
        holder.email.setText(list.get(position).email);
        holder.nim.setText(list.get(position).nim);

        String dataBerat = list.get(position).beratKG;
        String dataTinggi = list.get(position).tinggiCM;

        // Pengecekan nilai beratKG
        if (dataBerat != null && !dataBerat.isEmpty()) {
            int BB = Integer.parseInt(dataBerat);

            // Pengecekan nilai tinggiCM
            if (dataTinggi != null && !dataTinggi.isEmpty()) {
                int TB = Integer.parseInt(dataTinggi);
                double TBM = TB / 100.0;
                double BMI = BB / (TBM * TBM);

                String kategoriBMI;
                if (BMI < 18.5) {
                    kategoriBMI = "Berat badan kurang";
                } else if (BMI >= 18.5 && BMI < 23) {
                    kategoriBMI = "Berat badan normal";
                } else if (BMI >= 23 && BMI < 30) {
                    kategoriBMI = "Berat badan berlebih (kecenderungan obesitas)";
                } else {
                    kategoriBMI = "Obesitas";
                }

                holder.kategori.setText(kategoriBMI);
            } else {
                holder.kategori.setText("Tinggi tidak tersedia");
            }
        } else {
            holder.kategori.setText("Berat tidak tersedia");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView fullName, email, nim, kategori;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.full_name);
            email = itemView.findViewById(R.id.email);
            nim = itemView.findViewById(R.id.nim);
            kategori = itemView.findViewById(R.id.kategori);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

}
