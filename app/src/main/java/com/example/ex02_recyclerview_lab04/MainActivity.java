package com.example.ex02_recyclerview_lab04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RecyclerView view;

    MyAdapter adapter;
    ArrayList<String> data = new ArrayList<>();

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.RecyclerView);

        int n = new Random().nextInt(200);

        for (int i = 0; i < n; i++){
            data.add("Item " + i);
        }
        adapter = new MyAdapter();

        view.setAdapter(adapter);

        view.setLayoutManager(new LinearLayoutManager(this));
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View childView = inflater.inflate(R.layout.item, null);
            return new MyViewHolder(childView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.textView.setText(data.get(position));
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"Item " +  holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.remove(holder.getAdapterPosition());

                    adapter.notifyItemRemoved((holder.getAdapterPosition()));
                }
            });

        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button button;
        public MyViewHolder(View itemView) {
            super(itemView);
            // Get the layout
            textView = itemView.findViewById(R.id.item);

            button = itemView.findViewById(R.id.button);

//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = MyViewHolder.this.getAdapterPosition();
//
//                    data.remove(position);
//
//                    adapter.notifyItemRemoved(position);
//                }
//            });
        }
    }}