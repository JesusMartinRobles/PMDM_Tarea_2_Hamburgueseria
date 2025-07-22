package com.example.BurgersTakeAway;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.BurgersTakeAway.Component.AlertCustomError;

import java.util.Arrays;
import java.util.Objects;

import Models.Pedido;

public class PedidoActivity extends AppCompatActivity {
    Intent intent;
    Pedido pedido;
    Toolbar toolbarOrder;
    CheckBox chkVegano;
    TextView txtcount1, txtcount2, txtcount3;
    Button btnAdd1, btnAdd2, btnAdd3,
            btnAtras, botonLimpiar, btnSiguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        toolbarOrder = (Toolbar) findViewById(R.id.toolbarOrder);
        setSupportActionBar(toolbarOrder);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.toolbarPedido);

        intent = getIntent();
        pedido = (Pedido) intent.getSerializableExtra("myOrder");
        txtcount1 = (TextView) findViewById(R.id.con3Txt);
        txtcount2 = (TextView) findViewById(R.id.cont2Txt);
        txtcount3 = (TextView) findViewById(R.id.cont1Txt);
        btnAdd1 = (Button) findViewById(R.id.btnAdd3);
        btnAdd2 = (Button) findViewById(R.id.btnAdd2);
        btnAdd3 = (Button) findViewById(R.id.btnAdd1);
        btnSiguiente =(Button) findViewById(R.id.btnNext);
        botonLimpiar =(Button) findViewById(R.id.btnClear);
        btnAtras =(Button) findViewById(R.id.btnBack);
        chkVegano =(CheckBox) findViewById(R.id.chkVegano);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pedido.getBurgues().length>0) {
                NavigateDrinks();
                  
                 } else {
                    AlertCustomError alter = new AlertCustomError();
                    alter.getAlert(PedidoActivity.this,getString(R.string.pedidoProductos),getString(R.string.burguerEmpty),
                            android.app.AlertDialog.BUTTON_NEUTRAL,getString(R.string.OK)
                    ).show();
            }
        }
        });

        chkVegano.setOnCheckedChangeListener((buttonView, isChecked) -> {
                pedido.setIsVegano(isChecked);
        });
        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clear();
            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back();
            }
        });
        btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedido.addBurgues(1);
                int[] list = pedido.getBurgues();
                if(list!=null) {
                    txtcount1.setText("Cantidad: " + Arrays.stream(pedido.getBurgues()).filter(num -> num == 1).count());
                }else {
                    txtcount1.setText("Cantidad: " + 0);
                }

                }
        });
        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedido.addBurgues(2);
                txtcount2.setText(  "Cantidad: "+ Arrays.stream(pedido.getBurgues()).filter(num  -> num ==2).count());
            }
        });
        btnAdd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pedido.addBurgues(3);
                txtcount3.setText(  "Cantidad: "+ Arrays.stream(pedido.getBurgues()).filter(num  -> num ==3).count());
            }
        });
    }

    public void NavigateDrinks() {
        intent = new Intent(this, DrinksActivity.class);
        intent.putExtra("myOrder", pedido);
        startActivity(intent);
    }
    public void Clear() {
        pedido.clearBurger();
        pedido.setIsVegano(false);
        txtcount1.setText("Cantidad: "+0);
        txtcount2.setText("Cantidad: "+0);
        txtcount3.setText("Cantidad: "+0);
        chkVegano.setChecked(false);
    }
    public void Back() {
        this.finish();
    }

}
