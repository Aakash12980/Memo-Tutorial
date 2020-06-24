package com.myapp.memotutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myapp.memotutorial.adapter.CardAdapter;
import com.myapp.memotutorial.data.CardData;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private List<CardData> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FloatingActionButton newCard = findViewById(R.id.new_Card);


        data.add(new CardData("Homework", " In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content", "20/06/2020"));
        data.add(new CardData("Games", " In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content", "20/06/2020"));
        data.add(new CardData("Schools", " In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content", "20/06/2020"));
        data.add(new CardData("Series", " In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content", "20/06/2020"));

        RecyclerView recyclerView = findViewById(R.id.home_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CardAdapter adapter = new CardAdapter(data);
        recyclerView.setAdapter(adapter);

        newCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, CreateCard.class));
            }
        });

    }
}
