package Models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.TypedArrayUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Pedido implements Serializable {
    private  LocalDateTime fechaHoraPedido;
    private String nombre;
    private String telefono;
    private String direccion;
    private int[] burguer;

    private int[] drinks;
    private boolean esVegano;
    private boolean esADomicilio;

    public Pedido() {
        fechaHoraPedido = LocalDateTime.now();
        this.burguer = new int[]{};
        this.drinks = new int[]{};
    }
    public  LocalDateTime getDate(){
        return  this.fechaHoraPedido;
    }
    public String getNombre(){
        return  this.nombre;
    }
    public String getTelefono(){
        return  this.telefono ;
    }
    public String getDireccion(){
        return  this.direccion;
    }
    public int[] getBurgues(){
        return  this.burguer;
    }
    public int[] getDrinks(){
        return  this.drinks;
    }
    public boolean getIsVegano(){
        return  this.esVegano;
    }
    public boolean getADomiciolio(){
        return  this.esADomicilio;
    }
    public void setNombre(String name){
       this.nombre=name;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono ;
    }
    public void setDireccion(String direccion){
          this.direccion=direccion;
    }
    public void addBurgues(int burguer){
        int[] newArray = Arrays.copyOf( this.burguer,  this.burguer.length + 1);


        newArray[newArray.length - 1] = burguer;
        this.burguer = newArray;
    }
    public void addDrink(int drinks){
        int[] newArray = Arrays.copyOf( this.drinks,  this.drinks.length + 1);
        newArray[newArray.length - 1] = drinks;
        this.drinks = newArray;
    }
    public void clearDrinks(){
        this.drinks = new int []{};
    }
    public void clearBurger(){
        this.burguer = new int []{};
    }
    public void setIsVegano(boolean vegan){
        this.esVegano=vegan;
    }
    public void setADomiciolio(boolean esADomicilio){
        this.esADomicilio=esADomicilio;
    }
}
