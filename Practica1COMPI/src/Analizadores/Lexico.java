/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

import Clases.*;
import java.util.ArrayList;

/**
 *
 * @author Micky
 */
public class Lexico {

    private ArrayList<Token> listaT;
    private ArrayList<Clases.Error> listaE;
    private ArrayList<Conjunto> listaC;
    private ArrayList<Exprecion> listaR;
    private ArrayList<Lexema> listaL;

    public Lexico() {
        this.listaT = new ArrayList<>();
        this.listaE = new ArrayList<>();
        this.listaC = new ArrayList<>();
        this.listaR = new ArrayList<>();
        this.listaL = new ArrayList<>();

    }

    public void analizar(String entrada) {

        int fila = 1;
        int columna = 1;
        String lexema = "";
        char caracter = ' ';
        int estado = 0;
        int puntero = 0;
        int t = 1;
        int e = 1;

        String[] lineas = entrada.split("\n");

        while (fila - 1 < lineas.length) {
//            System.out.println("");

            while (puntero < lineas[fila - 1].length()) {
                caracter = lineas[fila - 1].charAt(puntero);
//                System.out.print(caracter);
//                
                switch (estado) {
                    case 0:
                        if (caracter == '\n' || caracter == '\t' || caracter == ' ') {
                            puntero++;
//                            System.out.print(" ");

                        } else if (caracter == '{') {
                            listaT.add(new Token(t, 1, caracter + "", "Llave que abre", fila, columna));
                            t++;
                            columna++;
                            puntero++;

                        } else if (caracter == '}') {
                            listaT.add(new Token(t, 2, caracter + "", "Llave que cierra", fila, columna));
                            t++;
                            columna++;
                            puntero++;

                        } else if (caracter == ':') {
                            listaT.add(new Token(t, 3, caracter + "", "Dos puntos", fila, columna));
                            t++;
                            columna++;
                            puntero++;

                        } else if (caracter == ';') {
                            listaT.add(new Token(t, 4, caracter + "", "Punto y coma", fila, columna));
                            t++;
                            columna++;
                            puntero++;

                        } else if (caracter == '.') {
                            listaT.add(new Token(t, 5, caracter + "", "Concatenacion", fila, columna));
                            t++;
                            columna++;
                            puntero++;

                        } else if (caracter == '|') {
                            listaT.add(new Token(t, 6, caracter + "", "Disyuncion", fila, columna));
                            t++;
                            columna++;
                            puntero++;

                        } else if (caracter == '?') {
                            listaT.add(new Token(t, 7, caracter + "", "Cerradura ?", fila, columna));
                            t++;
                            columna++;
                            puntero++;

                        } else if (caracter == '*') {
                            listaT.add(new Token(t, 8, caracter + "", "Cerradura de Kleen", fila, columna));
                            t++;
                            columna++;
                            puntero++;

                        } else if (caracter == '+') {
                            listaT.add(new Token(t, 9, caracter + "", "Cerradura +", fila, columna));
                            t++;
                            columna++;
                            puntero++;

                        } else if (caracter == '~') {
                            listaT.add(new Token(t, 10, caracter + "", "Conjuncion", fila, columna));
                            t++;
                            columna++;
                            puntero++;

                        } else if (caracter == ',') {
                            listaT.add(new Token(t, 11, caracter + "", "Coma", fila, columna));
                            t++;
                            columna++;
                            puntero++;

                        } else if (caracter == '-') {
                            lexema += caracter;
                            puntero++;
                            estado = 1;

                        } else if (caracter == '/') {
                            lexema += caracter;
                            puntero++;
                            estado = 1;

                        } else if (caracter == '<') {
                            lexema += caracter;
                            puntero++;
                            estado = 1;

                        } else if (caracter == '%') {
                            lexema += caracter;
                            puntero++;
                            estado = 1;

                        } else if (caracter == '"') {
                            puntero++;
                            estado = 6;

                        } else if (Character.isUpperCase(caracter)) {
                            lexema += caracter;
                            puntero++;
                            estado = 7;

                        } else if (Character.isLowerCase(caracter)) {
                            lexema += caracter;
                            puntero++;
                            estado = 8;

                        } else if (Character.isDigit(caracter)) {
                            listaT.add(new Token(t, 19, caracter + "", "Numero", fila, columna));
                            t++;
                            columna++;
                            puntero++;
                            estado = 0;

                        } else {
                            estado = 9;

                        }

                        break;

                    case 1:
                        if (lexema.equals("-") && caracter == '>') {
                            listaT.add(new Token(t, 12, lexema + caracter, "Asignacion", fila, columna));
                            lexema = "";
                            t++;
                            columna++;
                            puntero++;
                            estado = 0;
                        } else if (lexema.equals("/") && caracter == '/') {
                            puntero = lineas[fila - 1].length();
                            lexema = "";
                            estado = 0;
                            columna++;
                        } else if (lexema.equals("<") && caracter == '!') {
                            estado = 2;
                            lexema = "";
                            puntero++;
                        } else if (lexema.equals("%") && caracter == '%') {
                            estado = 4;
                            lexema += caracter;
                            puntero++;
                        } else {
                            estado = 9;
                        }

                        break;

                    case 2:
                        if (caracter == '!') {
                            estado = 3;
                            lexema += caracter;
                            puntero++;
                        } else {
                            puntero++;
                        }

                        break;

                    case 3:
                        if (caracter == '>') {
                            puntero++;
                            lexema = "";
                            estado = 0;
                        } else {
                            puntero++;
                            lexema = "";
                            estado = 2;
                        }

                        break;

                    case 4:
                        if (caracter == '%') {
                            estado = 5;
                            lexema += caracter;
                            puntero++;
                        } else {
                            estado = 9;
                        }

                        break;

                    case 5:
                        if (caracter == '%') {
                            listaT.add(new Token(t, 13, lexema + caracter, "Separador", fila, columna));
                            lexema = "";
                            t++;
                            columna++;
                            puntero++;
                            estado = 0;
                        } else {
                            estado = 9;
                        }

                        break;

                    case 6:
                        if (caracter == '"') {
                            listaT.add(new Token(t, 14, lexema, "Texto", fila, columna));
                            lexema = "";
                            t++;
                            columna++;
                            puntero++;
                            estado = 0;
                        } else {
                            lexema += caracter;
                            puntero++;
                            estado = 6;
                        }

                        break;

                    case 7:
                        if (caracter == ',' && lexema.length() == 1) {
                            listaT.add(new Token(t, 17, lexema, "Letra", fila, columna));
                            lexema = "";
                            t++;
                            columna++;
                            estado = 0;
                        } else if (Character.isLetter(caracter) || Character.isDigit(caracter) || caracter == '_') {
                            lexema += caracter;
                            puntero++;
                            estado = 7;
                        } else {
                            if (lexema.equals("CONJ")) {
                                listaT.add(new Token(t, 15, lexema, "Palabra reservada", fila, columna));
                            } else {
                                listaT.add(new Token(t, 16, lexema, "Identificador", fila, columna));
                            }
                            lexema = "";
                            t++;
                            columna++;
                            estado = 0;
                        }

                        break;

                    case 8:
                        if ((caracter == ',' || caracter == ';' || caracter == '~') && lexema.length() == 1) {
                            listaT.add(new Token(t, 17, lexema, "Letra", fila, columna));
                            lexema = "";
                            t++;
                            columna++;
                            estado = 0;
                        } else if (Character.isLetter(caracter)) {
                            lexema += caracter;
                            puntero++;
                            estado = 8;
                        } else {
                            listaT.add(new Token(t, 18, lexema, "Nombre", fila, columna));
                            lexema = "";
                            t++;
                            columna++;
                            estado = 0;
                        }

                        break;

                    case 9:
                        listaE.add(new Clases.Error(e, lexema + caracter, "Elemento lexico desconocido", fila, columna));
                        lexema = "";
                        puntero++;
                        e++;
                        columna++;
                        estado = 0;

                        break;

                    default:
                        break;
                }
            }
            fila++;
            puntero = 0;
            columna = 1;
        }

        if (listaE.isEmpty()) {
            conjuntos();
            expreciones();
        }

    }

    public void conjuntos() {
        int estado = 0;
        int i = 0;
        Conjunto temp = new Conjunto();

        while (i < listaT.size()) {
            switch (estado) {
                case 0:
                    if (listaT.get(i).getToken() == 15) {
                        estado = 1;
                    } else {
                        estado = 0;
                    }
                    break;

                case 1:
                    if (listaT.get(i).getToken() == 3) {
                        estado = 2;
                    } else {
                        estado = 0;
                    }
                    break;

                case 2:
                    if (listaT.get(i).getToken() == 18) {
                        estado = 3;
                        temp = new Conjunto();
                        temp.setNombre(listaT.get(i));
                    } else {
                        estado = 0;
                    }
                    break;

                case 3:
                    if (listaT.get(i).getToken() == 12) {
                        estado = 4;
                    } else {
                        estado = 0;
                    }
                    break;

                case 4:
                    if (listaT.get(i).getToken() == 17) {
                        estado = 5;
                        temp.getLista().add(listaT.get(i).getLexema().charAt(0));
                    } else if (listaT.get(i).getToken() == 19) {
                        estado = 7;
                        temp.getLista().add(listaT.get(i).getLexema().charAt(0));
                    } else {
                        estado = 0;
                    }
                    break;

                case 5:
                    if (listaT.get(i).getToken() == 11) {
                        estado = 4;
                    } else if (listaT.get(i).getToken() == 10) {
                        estado = 6;
                    } else {
                        i--;
                        estado = 9;
                    }
                    break;

                case 6:
                    if (listaT.get(i).getToken() == 17) {
                        char sub = temp.getLista().get(0);
                        sub++;
                        while (sub <= listaT.get(i).getLexema().charAt(0)) {
                            temp.getLista().add(sub);
                            sub++;
                        }
                    }
                    estado = 9;
                    break;

                case 7:
                    if (listaT.get(i).getToken() == 10) {
                        estado = 8;
                    } else {
                        estado = 9;
                    }
                    break;

                case 8:
                    if (listaT.get(i).getToken() == 19) {
                        char sub = temp.getLista().get(0);
                        sub++;
                        while (sub <= listaT.get(i).getLexema().charAt(0)) {
                            temp.getLista().add(sub);
                            sub++;
                        }
                    }
                    estado = 9;
                    break;

                case 9:
                    if (listaT.get(i).getToken() == 4) {
                        listaC.add(temp);
                    }
                    estado = 0;

                    break;

                default:
                    break;
            }

            i++;
        }

    }

    public void expreciones() {
        int estado = 0;
        int i = 0;
        Exprecion temp = new Exprecion();

        while (i < listaT.size()) {
            switch (estado) {
                case 0:
                    if (listaT.get(i).getToken() == 16 || listaT.get(i).getToken() == 18) {
                        temp = new Exprecion();
                        temp.setNombre(listaT.get(i));
                        estado = 1;
                    }else{
                        estado = 0;
                    }
                    break;

                case 1:
                    if (listaT.get(i).getToken() == 12) {
                        estado = 2;
                    }else{
                        estado = 0;
                    }
                    break;

                case 2:
                    int t = listaT.get(i).getToken();
                    
                    if (t == 1 || t == 2) {
                        estado = 2;
                    } else if (t == 5 || t == 6 || t == 7 || t == 8 || t == 9 || t == 14 || t == 18) {
                        temp.getExp().add(listaT.get(i));
                    } else if (t == 4) {
                        temp.getArbol().setLista(temp.getExp());
                        temp.getArbol().setNombre(temp.getNombre().getLexema());
                        temp.getArbol().graficarArbol();
                        temp.getArbol().graficarSiguientes();
                        listaR.add(temp);
                        estado = 0;
                    } else {
                        estado = 0;
                    }
                    break;

                default:
                    break;
            }
            i++;
        }
    }

    public ArrayList<Token> getListaT() {
        return listaT;
    }

    public void setListaT(ArrayList<Token> listaT) {
        this.listaT = listaT;
    }

    public ArrayList<Clases.Error> getListaE() {
        return listaE;
    }

    public void setListaE(ArrayList<Clases.Error> listaE) {
        this.listaE = listaE;
    }

    public ArrayList<Conjunto> getListaC() {
        return listaC;
    }

    public void setListaC(ArrayList<Conjunto> listaC) {
        this.listaC = listaC;
    }

    public ArrayList<Exprecion> getListaR() {
        return listaR;
    }

    public void setListaR(ArrayList<Exprecion> listaR) {
        this.listaR = listaR;
    }

    public ArrayList<Lexema> getListaL() {
        return listaL;
    }

    public void setListaL(ArrayList<Lexema> listaL) {
        this.listaL = listaL;
    }

}
