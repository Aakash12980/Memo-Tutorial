package com.myapp.memotutorial.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.memotutorial.R;
import com.myapp.memotutorial.data.CardData;

import java.util.Arrays;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardAdapterviewHolder> {
    private List<CardData> data;
    private List<String> colors = Arrays.asList("#FBFBFB", "#1FD456");
    private int counter = 0;

    public CardAdapter(List<CardData> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CardAdapterviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.card_view, parent, false);
        return new CardAdapterviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapterviewHolder holder, int position) {
        CardData card = data.get(position);
        holder.cardTitle.setText(card.getCardTitle());
        holder.cardBody.setText(card.getCardBody());
        holder.cardDate.setText(card.getCardDate());
        counter = counter == 1? 0:1;
        holder.mycard.setCardBackgroundColor(Color.parseColor(colors.get(counter)));



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CardAdapterviewHolder extends RecyclerView.ViewHolder{
        TextView cardTitle, cardBody, cardDate;
        CardView mycard;

        public CardAdapterviewHolder(@NonNull View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardBody = itemView.findViewById(R.id.card_body);
            cardDate = itemView.findViewById(R.id.card_date);
            mycard = itemView.findViewById(R.id.card_list);



        }
    }
}
