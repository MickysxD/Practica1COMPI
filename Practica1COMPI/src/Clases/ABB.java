/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Micky
 */
public class ABB {

    private NodoABB root;
    private ArrayList<Token> lista;
    private int i = 0;
    private int id = 1;
    private String nombre;

    public ABB() {
        NodoABB temp = new NodoABB();
        temp.setIdToken(5);
        temp.setIdNodo(0);
        temp.setNombre(".");
        this.root = temp;
        temp = new NodoABB();
        temp.setIdNodo(1);
        temp.setIdToken(0);
        temp.setNombre("#");
        this.root.setDer(temp);
    }

    public void rellenar(NodoABB raiz) {
        int r = raiz.getIdToken();
        if (i < lista.size()) {
            if (r == 5) {
                if (raiz.getIzq() == null) {
                    raiz.setIzq(agregar());
                    rellenar(raiz.getIzq());
                }

                if (raiz.getDer() == null) {
                    raiz.setDer(agregar());
                    rellenar(raiz.getDer());
                }

                if (raiz.getIzq().isAnulable() && raiz.getDer().isAnulable()) {
                    raiz.setAnulable(true);
                } 
                
                if (raiz.getIzq().isAnulable()) {
                    primeros(raiz);
                } else {
                    raiz.setPrimeros(raiz.getIzq().getPrimeros());
                }
                
                if (raiz.getDer().isAnulable()) {
                    ultimos(raiz);
                } else {
                    raiz.setUltimos(raiz.getDer().getUltimos());
                }

            } else if (r == 6) {
                if (raiz.getIzq() == null) {
                    raiz.setIzq(agregar());
                    rellenar(raiz.getIzq());
                }

                if (raiz.getDer() == null) {
                    raiz.setDer(agregar());
                    rellenar(raiz.getDer());
                }

                if (raiz.getIzq().isAnulable() || raiz.getDer().isAnulable()) {
                    raiz.setAnulable(true);
                }

                primeros(raiz);
                ultimos(raiz);

            } else if (r == 7 || r == 8 || r == 9) {
                if (raiz.getIzq() == null) {
                    raiz.setIzq(agregar());
                    rellenar(raiz.getIzq());
                    if (r == 9 && raiz.getIzq().isAnulable()) {
                        raiz.setAnulable(true);
                    }
                    if (r == 7 || r == 8) {
                        raiz.setAnulable(true);
                    }
                    raiz.setPrimeros(raiz.getIzq().getPrimeros());
                    raiz.setUltimos(raiz.getIzq().getUltimos());
                }

            }

        } else {
            this.root.getDer().setIdMetodo(id);
            this.root.getDer().getPrimeros().add(id);
            this.root.getDer().getUltimos().add(id);
            id = 1;
            i = 0;
        }
    }

    public NodoABB agregar() {
        NodoABB temp = new NodoABB();
        temp.setIdToken(lista.get(i).getToken());
        temp.setNombre(lista.get(i).getLexema());
        temp.setIdNodo(i + 2);
        if (lista.get(i).getToken() == 14 || lista.get(i).getToken() == 18) {
            temp.setIdMetodo(id);
            temp.getPrimeros().add(id);
            temp.getUltimos().add(id);
            id++;
        }
        i++;

        return temp;
    }

    public void primeros(NodoABB raiz) {
        for (int j = 0; j < raiz.getIzq().getPrimeros().size(); j++) {
            raiz.getPrimeros().add(raiz.getIzq().getPrimeros().get(j));
        }

        for (int j = 0; j < raiz.getDer().getPrimeros().size(); j++) {
            raiz.getPrimeros().add(raiz.getDer().getPrimeros().get(j));
        }

    }

    public void ultimos(NodoABB raiz) {
        for (int j = 0; j < raiz.getIzq().getUltimos().size(); j++) {
            raiz.getUltimos().add(raiz.getIzq().getUltimos().get(j));
        }

        for (int j = 0; j < raiz.getDer().getUltimos().size(); j++) {
            raiz.getUltimos().add(raiz.getDer().getUltimos().get(j));
        }

    }
    
    public String g(NodoABB root) {
        String retorno = "";
        String rank = "";

        if (root.getIzq() != null || root.getDer() != null) {
            if (root.getIdToken() == 6) {
                retorno = "\"" + root.getIdNodo() + "\"[label= \"<C0> " + root.getPrimeros().toString() + "| {" + root.isAnulable() + "|\\" + root.getNombre() + "}|<C1>" + root.getUltimos().toString() + "\"];\n";
            } else {
                retorno = "\"" + root.getIdNodo() + "\"[label= \"<C0> " + root.getPrimeros().toString() + "| {" + root.isAnulable() + "|" + root.getNombre() + "}|<C1>" + root.getUltimos().toString() + "\"];\n";
            }
            rank = "{rank=same; ";
        } else {
            if (root.getIdToken() == 6) {
                retorno = "\"" + root.getIdNodo() + "\"[label= \"<C0> " + root.getPrimeros().toString() + "| {\\" + root.isAnulable() + "|" + root.getNombre() + "|" + root.getIdMetodo() + "}|<C1>" + root.getUltimos().toString() + "\"];\n";
            } else {
                retorno = "\"" + root.getIdNodo() + "\"[label= \"<C0> " + root.getPrimeros().toString() + "| {" + root.isAnulable() + " |" + root.getNombre() + "|" + root.getIdMetodo() + "}|<C1>" + root.getUltimos().toString() + "\"];\n";
            }
        }

        if (root.getIzq() != null) {
            retorno = retorno + g(root.getIzq());
            retorno = retorno + "\"" + root.getIdNodo() + "\":C0->\"" + root.getIzq().getIdNodo() + "\";\n";
            rank = rank + "\"" + root.getIzq().getIdNodo() + "\" ";
        }

        if (root.getDer() != null) {
            retorno = retorno + g(root.getDer());
            retorno = retorno + "\"" + root.getIdNodo() + "\":C1->\"" + root.getDer().getIdNodo() + "\";\n";
            rank = rank + "\"" + root.getDer().getIdNodo() + "\" ";
        }

        if (root.getIzq() != null || root.getDer() != null) {
            rank = rank + "}\n";
        }

        retorno = retorno + rank;
        return retorno;

    }

    public boolean graficar() {
        FileWriter fichero;
        PrintWriter pw;
        try {
            fichero = new FileWriter("Reportes/" + this.nombre + ".txt");
            pw = new PrintWriter(fichero);

            pw.write("digraph grafico{\ngraph [nodesep=2];\nnode [shape=record]\nrankdir=TB;\n");

            //Creacion de nodos
            if (this.root != null) {
                String datos = g(this.root);
                pw.append(datos);
            }

            pw.append("}");

            pw.close();

            try {
                Runtime.getRuntime().exec("dot -Tjpg Reportes/" + this.nombre + ".txt -o Reportes/" + this.nombre + ".jpg");
                //Runtime.getRuntime().exec("cmd /c start " + this.nombre + ".txt");
                Runtime.getRuntime().exec("cmd /c start Reportes/" + this.nombre + ".jpg");
            } catch (IOException ioe) {
                System.out.println(ioe);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public NodoABB getRoot() {
        return root;
    }

    public void setRoot(NodoABB root) {
        this.root = root;
    }

    public ArrayList<Token> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Token> lista) {
        this.lista = lista;
        rellenar(this.root);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "ABB{" + "root=" + root + ", lista=" + lista + ", i=" + i + ", id=" + id + '}';
    }

}
