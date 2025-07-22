package com.example.BurgersTakeAway;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.BurgersTakeAway.Component.AlertCustomError;

import java.util.Objects;

import Models.Pedido;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

   private Intent intent;
    private Pedido pedido;
    private EditText inputNombre, inputTelefono, inputDireccion;
    private Spinner spinnerRecogida;
    private Button botonAtras, botonLimpiar, botonSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbarLogin = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbarLogin);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.toolbarLogin);

        intent = getIntent();
        pedido = (Pedido) getIntent().getSerializableExtra("myOrder");
        spinnerRecogida = findViewById(R.id.spinnerRecogida);
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this,R.array.arrayRecogida, android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRecogida.setAdapter(adaptador);
        spinnerRecogida.setOnItemSelectedListener(this);

        inputNombre = (EditText) findViewById(R.id.inptName);
        inputTelefono = (EditText) findViewById(R.id.inptTelefono);
        inputDireccion = (EditText) findViewById(R.id.inptDireccion);

        getValoresRegistro();

        botonAtras = (Button) findViewById(R.id.btnBack);
        botonLimpiar = (Button) findViewById(R.id.btnClear);
        botonSiguiente = (Button) findViewById(R.id.btnNext);

        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigatePantallaPrincipal();
            }
        });

        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        botonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputNombre.length() == 0 || inputTelefono.length() != 9 || (spinnerRecogida.getSelectedItemPosition() == 1 && inputDireccion.length() == 0)) {
                    AlertCustomError alter = new AlertCustomError();
                    alter.getAlert(Login.this,getString(R.string.datosIncorrectos),getString(R.string.datosIncorrectosmsm),
                            AlertDialog.BUTTON_NEUTRAL,getString(R.string.OK)
                    ).show();

                } else {
                    pedido.setNombre(String.valueOf(inputNombre.getText()));
                    pedido.setTelefono(String.valueOf(inputTelefono.getText()));
                    if (spinnerRecogida.getSelectedItemPosition() == 1) {
                        pedido.setADomiciolio(true);
                        pedido.setDireccion(String.valueOf(inputDireccion.getText()));
                    } else{
                        pedido.setADomiciolio(false);
                    }
                    NavigateBurguer();
                }
            }
        });
    }
    public void getValoresRegistro() {
        inputNombre.setText(pedido.getNombre());
        inputTelefono.setText(pedido.getTelefono());
        if (pedido.getADomiciolio()){
            spinnerRecogida.setSelection(1);
        }
        else {
            spinnerRecogida.setSelection(0);
        }
        inputDireccion.setText(pedido.getDireccion());
    }
    public void navigatePantallaPrincipal() {
        finish();
    }
    public void clear() {
        EditText inputNombre = (EditText) findViewById(R.id.inptName);
        EditText inputTelefono = (EditText) findViewById(R.id.inptTelefono);
        EditText inputDireccion = (EditText) findViewById(R.id.inptDireccion);
        inputNombre.setText(null);
        inputTelefono.setText(null);
        inputDireccion.setText(null);
    }
    public void NavigateBurguer() {
        intent = new Intent(this, PedidoActivity.class);
        intent.putExtra("myOrder", pedido);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int posicion, long l) {
        TextView labelDireccion = (TextView) findViewById(R.id.lblDireccion);
        EditText inputDireccion = (EditText) findViewById(R.id.inptDireccion);

        if (posicion == 0) {
            labelDireccion.setVisibility(View.INVISIBLE);
            inputDireccion.setVisibility(View.INVISIBLE);
            inputDireccion.setText(null);
        }

        if (posicion == 1) {
            labelDireccion.setVisibility(View.VISIBLE);
            inputDireccion.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}
