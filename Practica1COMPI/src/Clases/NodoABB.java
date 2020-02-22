/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Micky
 */
public class NodoABB {
    private NodoABB izq;
    private NodoABB der;
    private int idToken;
    private int idMetodo;
    private int idNodo;
    private ArrayList<Integer> primeros;
    private ArrayList<Integer> ultimos;
    private ArrayList<Integer> siguientes;
    private boolean anulable;
    private String nombre;

    public NodoABB() {
        this.izq = null;
        this.der = null;
        this.primeros = new ArrayList<>();
        this.ultimos = new ArrayList<>();
        this.siguientes = new ArrayList<>();
        this.anulable = false;
    }

    public void siguientes(int hoja){
        boolean t = true;
        for (int i = 0; i < siguientes.size(); i++) {
            if(siguientes.get(i) == hoja){
                t = false;
            }
        }
        
        if(t){
            siguientes.add(hoja);
        }
        
        Collections.sort(siguientes);
    }
    
    public NodoABB getIzq() {
        return izq;
    }

    public void setIzq(NodoABB izq) {
        this.izq = izq;
    }

    public NodoABB getDer() {
        return der;
    }

    public void setDer(NodoABB der) {
        this.der = der;
    }

    public int getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(int idMetodo) {
        this.idMetodo = idMetodo;
    }

    public int getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(int idNodo) {
        this.idNodo = idNodo;
    }

    public boolean isAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public ArrayList<Integer> getPrimeros() {
        return primeros;
    }

    public void setPrimeros(ArrayList<Integer> primeros) {
        this.primeros = primeros;
    }

    public ArrayList<Integer> getUltimos() {
        return ultimos;
    }

    public void setUltimos(ArrayList<Integer> ultimos) {
        this.ultimos = ultimos;
    }

    public int getIdToken() {
        return idToken;
    }

    public void setIdToken(int idToken) {
        this.idToken = idToken;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(ArrayList<Integer> siguientes) {
        this.siguientes = siguientes;
    }
    
}
