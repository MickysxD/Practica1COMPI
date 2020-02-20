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
public class Exprecion {
    private ABB arbol;
    private Token nombre;
    private ArrayList<Token> exp;

    public Exprecion() {
        this.arbol = new ABB();
        this.nombre = new Token();
        this.exp = new ArrayList<>();
    }

    public ABB getArbol() {
        return arbol;
    }

    public void setArbol(ABB arbol) {
        this.arbol = arbol;
    }

    public Token getNombre() {
        return nombre;
    }

    public void setNombre(Token nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Token> getExp() {
        return exp;
    }

    public void setExp(ArrayList<Token> exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "Exprecion{" + "arbol=" + arbol + ", nombre=" + nombre + ", exp=" + exp + '}';
    }
    
}
