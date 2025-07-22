package com.example.BurgersTakeAway;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

import Models.Pedido;

public class ResumemActivity extends AppCompatActivity {
    Intent intent;
    Pedido pedido;
    Toolbar toolbarOrder;
    TextView textResumen;
    Button btnBack;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        toolbarOrder = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbarOrder);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.toolbarResumen);
        intent = getIntent();
        pedido = (Pedido) intent.getSerializableExtra("myOrder");

        textResumen = (TextView) findViewById(R.id.textResumen);
        textResumen.setText(getResumen());
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigateMain();
            }});
    }

    private String getResumen(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String tipoEntrega = "";
        String recargo = "";
        String pedidos = "";
        String Total = "";
        double totalOrder = getBurgersTotal()+ getDrinksTotal();

        if(pedido.getADomiciolio()){
            tipoEntrega =  "Tipo de Entrega: A dominicilio"+ "\n"+ "Dirección: " + pedido.getDireccion()+"\n";
            recargo = "Recargo a domicilio: 2.99 €";
            totalOrder = totalOrder+ 2.99;
        }else{
            tipoEntrega= "Tipo de Entrega: Recogida"+"\n";
        }
        pedidos = pedidos + getBurgersString()+ getDrinksString()+"\n";
        Total = "\n"+ "Total : " +"\t"+ totalOrder+" €"+ "\n"+
                "Iva :"+"\t" + "7 %"+"\n"+
                "Total IVA : "+"\t"+( Math.round(totalOrder* (1.07)* Math.pow(10, 2)) / Math.pow(10, 2))+"\n"+"\n";

        String result = "Fecha: "+  pedido.getDate().format(formatter) + "\n"+ "\n"+
                "Nombre: " + pedido.getNombre()+ "\n" +
                "Teléfono: " + pedido.getTelefono()+ "\n" + tipoEntrega + "\n" +
                "____________________"+"\n"+
                "Pedido: "+"\n"+
                pedidos +
                recargo+"\n"+
                Total;


        return result;
    }

    private String getBurgersString(){
        String pedidos = "";
        String vegano = "";
        if(pedido.getIsVegano()) {
        vegano = " (Vegano)";
        }
        int[] listaBurgers1  =Arrays.stream(pedido.getBurgues()).filter(num  -> num ==1).toArray();
        int[] listaBurgers2  =Arrays.stream(pedido.getBurgues()).filter(num  -> num ==2).toArray();
        int[] listaBurgers3  =Arrays.stream(pedido.getBurgues()).filter(num  -> num ==3).toArray();


        if(listaBurgers1.length > 0){
            pedidos = pedidos +" * " + listaBurgers1.length + " Burger BBQ"+vegano+": "+"\t" + (11.95*listaBurgers1.length)+" €" +"\n";
        }
        if(listaBurgers2.length > 0){
            pedidos = pedidos +" * " + listaBurgers2.length + " Burger M30"+vegano+": " +"\t"+ (13.95*listaBurgers2.length)+" €" +"\n";
        }
        if(listaBurgers3.length > 0){
            pedidos = pedidos +" * " + listaBurgers3.length + " Chess Burger:"+vegano+": " +"\t" + (13.50*listaBurgers3.length)+" €" +"\n";
        }
      return pedidos;
    }
    private Double getBurgersTotal(){
        double total = 0;
        int[] listaBurgers1  =Arrays.stream(pedido.getBurgues()).filter(num  -> num ==1).toArray();
        int[] listaBurgers2  =Arrays.stream(pedido.getBurgues()).filter(num  -> num ==2).toArray();
        int[] listaBurgers3  =Arrays.stream(pedido.getBurgues()).filter(num  -> num ==3).toArray();


        if(listaBurgers1.length > 0){
            total= total+ Math.floor(100*(11.95*listaBurgers1.length))/100;
        }
        if(listaBurgers2.length > 0){
            total= total+ Math.floor(100*(13.95*listaBurgers2.length))/100;
        }
        if(listaBurgers3.length > 0){
            total= total+ Math.floor(100*(13.50*listaBurgers3.length))/100;

        }
        return Math.round(total * Math.pow(10, 2)) / Math.pow(10, 2);
    }
    private String getDrinksString(){

        String pedidos = "";


        int[] listaDrinks1  =Arrays.stream(pedido.getDrinks()).filter(num  -> num ==1).toArray();
        int[] listaDrinks2  =Arrays.stream(pedido.getDrinks()).filter(num  -> num ==2).toArray();
        int[] listaDrinks3  =Arrays.stream(pedido.getDrinks()).filter(num  -> num ==3).toArray();
        int[] listaDrinks4  =Arrays.stream(pedido.getDrinks()).filter(num  -> num ==4).toArray();

        if(listaDrinks1.length > 0){
            pedidos = pedidos +" * " + listaDrinks1.length + " Pepsi: "+"\t" + (2.30*listaDrinks1.length)+" €" +"\n";
        }
        if(listaDrinks2.length > 0){
            pedidos = pedidos +" * " + listaDrinks2.length + " Agua: "+"\t" + (1.50*listaDrinks2.length)+" €" +"\n";
        }
        if(listaDrinks3.length > 0){
            pedidos = pedidos +" * " + listaDrinks3.length + " Alhambra: "+"\t" + (2.50*listaDrinks3.length)+" €" +"\n";
        }
        if(listaDrinks4.length > 0){
            pedidos = pedidos +" * " + listaDrinks4.length + " Kas: "+"\t" +(2.50*listaDrinks4.length)+" €" +"\n";
        }

        return pedidos;
    }
    private Double getDrinksTotal(){

        double total = 0;


        int[] listaDrinks1  =Arrays.stream(pedido.getDrinks()).filter(num  -> num ==1).toArray();
        int[] listaDrinks2  =Arrays.stream(pedido.getDrinks()).filter(num  -> num ==2).toArray();
        int[] listaDrinks3  =Arrays.stream(pedido.getDrinks()).filter(num  -> num ==3).toArray();
        int[] listaDrinks4  =Arrays.stream(pedido.getDrinks()).filter(num  -> num ==4).toArray();

        if(listaDrinks1.length > 0){
            total= total+ Math.floor(100*(2.30*listaDrinks1.length))/100;
        }
        if(listaDrinks2.length > 0){
            total= total+ Math.floor(100*(1.50*listaDrinks2.length))/100;

        }
        if(listaDrinks3.length > 0){
            total= total+ Math.floor(100*(2.50*listaDrinks3.length))/100;
                   }
        if(listaDrinks4.length > 0){
            total= total+ Math.floor(100*(2.50*listaDrinks4.length))/100;
        }

        return Math.round(total* Math.pow(10, 2)) / Math.pow(10, 2);
    }
    private void NavigateMain(){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
           this.finish();
    }
}
