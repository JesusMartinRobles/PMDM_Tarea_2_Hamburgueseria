package com.example.BurgersTakeAway;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.LocaleConfig;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import Models.Pedido;

public class MainActivity extends AppCompatActivity {
    private Button btnOrder;
    private  boolean clickTolBoard=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.mainmenu);
        setSupportActionBar(toolbar);

        btnOrder = (Button) findViewById(R.id.btnOrder);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigateLogin();
            }
        });


    }

    public void NavigateAyuda(MenuItem ayuda) {
        Intent intent = new Intent(this, AyudaActivity.class);
        startActivity(intent);
    }
    public void NavigateAcercaDe(MenuItem ayuda) {
        Intent intent = new Intent(this, AcercaDeActivity.class);
        startActivity(intent);
    }
    public void NavigateLogin() {
        Pedido order = new Pedido();
        Intent intent = new Intent(this, Login.class);
        intent.putExtra("myOrder", order);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        this.finishAffinity();
        System.exit(0);
    }

}