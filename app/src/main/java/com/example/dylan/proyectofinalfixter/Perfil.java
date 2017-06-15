package com.example.dylan.proyectofinalfixter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Perfil extends AppCompatActivity {

    Button btnChat, btnPost, btnCerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        btnChat=(Button)findViewById(R.id.btnChat);
        btnPost=(Button)findViewById(R.id.BtnPost);


        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentito= new Intent(getApplicationContext(),Entrada.class);
                startActivity(intentito);
            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentito2= new Intent(getApplicationContext(),Chat.class);
                startActivity(intentito2);
            }
        });
    }
}
