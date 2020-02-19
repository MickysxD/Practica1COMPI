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
public class Conjunto {
    private Token nombre;
    private ArrayList<Character> lista;

    public Conjunto() {
        this.nombre = null;
        this.lista = new ArrayList<Character>();
    }

    public Token getNombre() {
        return nombre;
    }

    public void setNombre(Token nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Character> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Character> lista) {
        this.lista = lista;
    }

    @Override
    public String toString() {
        return "Conjunto{" + "nombre=" + nombre + ", lista=" + lista + '}';
    }
    
    
}
