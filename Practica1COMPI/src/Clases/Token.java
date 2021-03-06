/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Micky
 */
public class Token {
    
    private int no;
    private int token;
    private String lexema;
    private String tipo;
    private int fila;
    private int columna;

    public Token() {
    }

    public Token(int no, int token, String lexema, String tipo, int fila, int columna) {
        this.no = no;
        this.token = token;
        this.lexema = lexema;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Token{" + "no=" + no + ", token=" + token + ", lexema=" + lexema + ", tipo=" + tipo + ", fila=" + fila + ", columna=" + columna + '}';
    }

}
