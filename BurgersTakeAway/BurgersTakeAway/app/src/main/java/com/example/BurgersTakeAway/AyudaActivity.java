package com.example.BurgersTakeAway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class AyudaActivity extends AppCompatActivity {
    private Button botonAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        Toolbar toolbarAyuda = (Toolbar) findViewById(R.id.toolbarAyuda);
        setSupportActionBar(toolbarAyuda);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.toolbarTitleAyuda);

        botonAtras = (Button) findViewById(R.id.botonAtrasDeAyuda);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aPantallaPrincipal();
            }
        });

    }
    public void aPantallaPrincipal() {
        this.finish();
    }
}
