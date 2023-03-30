package com.example.task.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.Model.Result;
import com.example.task.R;

import java.util.ArrayList;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    ArrayList<Result> results = new ArrayList<>();

    private UserAdapterListener userAdapterListener;

    public Adapter(ArrayList<Result> list, UserAdapterListener userAdapterListener) {
        results = list;
        this.userAdapterListener = userAdapterListener;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desion, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        Result result = results.get(position);
        holder.set(result);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAdapterListener.onItemlick(result);
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        TextView text2;
        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text1 = (TextView) itemView.findViewById(R.id.Reyname);
            text2 = (TextView) itemView.findViewById(R.id.Reyemail);
            card = (CardView) itemView.findViewById(R.id.card);

        }

        public void set(Result result) {
            String name2 = null;
            String value = result.getEmail();
            String values[] = result.getName().split("_");
            String name=values[0];
            text1.setText(name.toLowerCase(Locale.ROOT) +result.getName().replace(name,""));
            text2.setText(value.toLowerCase(Locale.ROOT));
        }
    }

    public interface UserAdapterListener {
        void onItemlick(Result result);
    }

}





