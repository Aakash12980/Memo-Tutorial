package com.myapp.memotutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.myapp.memotutorial.data.CardData;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CreateCard extends AppCompatActivity {
    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);

        final EditText cardTitle = findViewById(R.id.create_card_title);
        final EditText cardBody = findViewById(R.id.create_card_body);
        MaterialToolbar appbar = findViewById(R.id.create_appbar);
        appbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateCard.super.onBackPressed();
            }
        });
        appbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.save_btn:
                        String title = cardTitle.getText().toString().trim();
                        String body = cardBody.getText().toString().trim();
                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = new Date();
                        String createDate = format.format(date);

                        CardData cardInfo = new CardData(title, body, createDate);

                        firestore.collection("Cards").document().set(cardInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(CreateCard.this, "Card saved successfully", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(CreateCard.this, "failed to save card.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        Toast.makeText(CreateCard.this, title+ " "+ body +" "+ createDate, Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return true;
                }
            }
        });

    }
}
