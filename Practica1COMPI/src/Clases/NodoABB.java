/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author Micky
 */
public class NodoABB {
    private NodoABB izq;
    private NodoABB der;
    private int id;
    private boolean anulable;
    private ArrayList<Integer> primeros;
    private ArrayList<Integer> ultimos;
    private int idToken;

    public NodoABB() {
        this.izq = null;
        this.der = null;
        this.primeros = new ArrayList<Integer>();
        this.ultimos = new ArrayList<Integer>();
        this.anulable = false;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
}
