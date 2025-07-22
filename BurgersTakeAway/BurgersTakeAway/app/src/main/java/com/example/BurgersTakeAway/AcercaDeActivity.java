package com.example.BurgersTakeAway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.BurgersTakeAway.MainActivity;
import com.example.BurgersTakeAway.R;

import java.util.Objects;

public class AcercaDeActivity extends AppCompatActivity {
    private Button botonAtras;
    private TextView txtAcerca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca);

        Toolbar toolbarAyuda = (Toolbar) findViewById(R.id.toolbarAcerca);
        setSupportActionBar(toolbarAyuda);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.lblAcercaDe);
        txtAcerca = (TextView) findViewById(R.id.txtAcerca);
        txtAcerca.setText(getString(R.string.txtAcerca1) + "\n"+getString(R.string.txtAcerca2));
        botonAtras = (Button) findViewById(R.id.btnAtras);
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
