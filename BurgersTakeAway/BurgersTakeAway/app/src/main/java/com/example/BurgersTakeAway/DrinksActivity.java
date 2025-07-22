package com.example.BurgersTakeAway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Arrays;
import java.util.Objects;

import Models.Pedido;

public class DrinksActivity extends AppCompatActivity {
    Intent intent;
    Pedido pedido;
    Toolbar toolbarOrder;
    TextView txtcount1, txtcount2, txtcount3,txtcount4;
    Button btnAdd1, btnAdd2, btnAdd3,btnAdd4,
            btnAtras, btnLimpiar, btnSiguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        toolbarOrder = (Toolbar) findViewById(R.id.toolbarOrder);
        setSupportActionBar(toolbarOrder);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.toolbarDrink);

        intent = getIntent();
        pedido = (Pedido) intent.getSerializableExtra("myOrder");
        txtcount1 = (TextView) findViewById(R.id.cont1Txt);
        txtcount2 = (TextView) findViewById(R.id.cont2Txt);
        txtcount3 = (TextView) findViewById(R.id.cont3Txt);
        txtcount4 = (TextView) findViewById(R.id.cont4Txt);
        btnAdd1 = (Button) findViewById(R.id.btnAdd1);
        btnAdd2 = (Button) findViewById(R.id.btnAdd2);
        btnAdd3 = (Button) findViewById(R.id.btnAdd3);
        btnAdd4 = (Button) findViewById(R.id.btnAdd4);
        btnAtras = (Button) findViewById(R.id.btnBack);
        btnLimpiar = (Button) findViewById(R.id.btnClear);
        btnSiguiente = (Button) findViewById(R.id.btnNext);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigateResumen();
            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clear();
            }
        });
        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    txtcount1.setText("Cantidad: " + addCountDrink(1));
            }
        });
        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtcount2.setText("Cantidad: " + addCountDrink(2));
            }
        });
        btnAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtcount3.setText("Cantidad: " + addCountDrink(3));
            }
        });
        btnAdd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtcount4.setText("Cantidad: " + addCountDrink(4));
            }
        });

    }

    private  void Clear(){
        txtcount1.setText("Cantidad: "+0);
        txtcount2.setText("Cantidad: "+0);
        txtcount3.setText("Cantidad: "+0);
        txtcount4.setText("Cantidad: "+0);
        pedido.clearDrinks();
    }

    public void back() {
        this.finish();
    }
    public void NavigateResumen() {
        intent = new Intent(this, ResumemActivity.class);
        intent.putExtra("myOrder", pedido);
        startActivity(intent);
    }

    private int addCountDrink(int drink){
        pedido.addDrink(drink);
        int[] list = pedido.getDrinks();
        int result = 0;

        if(list!=null) {
            result = (int) Arrays.stream(list).filter(num -> num == drink).count();
        }
        return  result;
    }

    @Override
    public void finish() {
    this.finish();
    }
}
