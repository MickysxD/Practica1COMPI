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
import java.util.Collections;

/**
 *
 * @author Micky
 */
public class ABB {

    private NodoABB root;
    private ArrayList<Token> lista;
    private ArrayList<NodoABB> hojas;
    private int i = 0;
    private int id = 1;
    private String nombre;

    public ABB() {
        this.lista = new ArrayList<>();
        this.hojas = new ArrayList<>();
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

                //
                for (int j = 0; j < raiz.getIzq().getUltimos().size(); j++) {
                    for (int k = 0; k < raiz.getDer().getPrimeros().size(); k++) {
                        siguientes(raiz.getIzq().getUltimos().get(j), raiz.getDer().getPrimeros().get(k));
                    }
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

                    if (r == 8 || r == 9) {
                        for (int j = 0; j < raiz.getUltimos().size(); j++) {
                            for (int k = 0; k < raiz.getPrimeros().size(); k++) {
                                siguientes(raiz.getUltimos().get(j), raiz.getPrimeros().get(k));
                            }
                        }
                    }
                }

            }

        } else {
            this.root.getDer().setIdMetodo(id);
            this.root.getDer().getPrimeros().add(id);
            this.root.getDer().getUltimos().add(id);
            hojas.add(this.root.getDer());
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
            hojas.add(temp);
            id++;
        }
        i++;

        return temp;
    }

    public void primeros(NodoABB raiz) {
        for (int j = 0; j < raiz.getIzq().getPrimeros().size(); j++) {
            boolean t = true;
            for (int k = 0; k < raiz.getPrimeros().size(); k++) {

                if (raiz.getPrimeros().get(k) == raiz.getIzq().getPrimeros().get(j)) {
                    t = false;
                }
            }

            if (t) {
                raiz.getPrimeros().add(raiz.getIzq().getPrimeros().get(j));
            }
        }

        for (int j = 0; j < raiz.getDer().getPrimeros().size(); j++) {
            boolean t = true;
            for (int k = 0; k < raiz.getPrimeros().size(); k++) {

                if (raiz.getPrimeros().get(k) == raiz.getDer().getPrimeros().get(j)) {
                    t = false;
                }
            }

            if (t) {
                raiz.getPrimeros().add(raiz.getDer().getPrimeros().get(j));
            }
        }

        Collections.sort(raiz.getPrimeros());

    }

    public void ultimos(NodoABB raiz) {
        for (int j = 0; j < raiz.getIzq().getUltimos().size(); j++) {
            boolean t = true;
            for (int k = 0; k < raiz.getUltimos().size(); k++) {

                if (raiz.getUltimos().get(k) == raiz.getIzq().getUltimos().get(j)) {
                    t = false;
                }
            }

            if (t) {
                raiz.getUltimos().add(raiz.getIzq().getUltimos().get(j));
            }

        }

        for (int j = 0; j < raiz.getDer().getUltimos().size(); j++) {
            boolean t = true;
            for (int k = 0; k < raiz.getUltimos().size(); k++) {

                if (raiz.getUltimos().get(k) == raiz.getDer().getUltimos().get(j)) {
                    t = false;
                }
            }

            if (t) {
                raiz.getUltimos().add(raiz.getDer().getUltimos().get(j));
            }
        }

        Collections.sort(raiz.getUltimos());

    }

    public void siguientes(int id, int hoja) {
        for (int j = 0; j < hojas.size(); j++) {
            if (hojas.get(j).getIdMetodo() == id) {
                hojas.get(j).siguientes(hoja);
            }
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

    public boolean graficarArbol() {
        FileWriter fichero;
        PrintWriter pw;
        try {
            fichero = new FileWriter("Reportes/" + this.nombre + "Arbol.txt");
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
                Runtime.getRuntime().exec("dot -Tjpg Reportes/" + this.nombre + "Arbol.txt -o Reportes/" + this.nombre + "Arbol.jpg");
                //Runtime.getRuntime().exec("cmd /c start " + this.nombre + ".txt");
                //Runtime.getRuntime().exec("cmd /c start Reportes/" + this.nombre + "Arbol.jpg");
            } catch (IOException ioe) {
                System.out.println(ioe);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public void graficarSiguientes() {
        FileWriter fichero;
        PrintWriter pw;
        try {
            fichero = new FileWriter("Reportes/" + this.nombre + "Siguientes.txt");
            pw = new PrintWriter(fichero);

            pw.write("digraph grafico{\ngraph [pad=\"0.5\", nodesep=\"0.5\", ranksep=\"2\"];\nnode [shape=plain]\nrankdir=LR;\n");
            pw.append("Foo [label=<\n<table border=\"0\" cellborder=\"1\" cellspacing=\"0\">\n<tr><td><i><b>Hoja</b></i></td><td><i><b>Id</b></i></td><td><i><b>Siguientes</b></i></td></tr>\n");
            
            for (int i = 0; i < hojas.size(); i++) {
                pw.append("<tr><td><b>" + hojas.get(i).getNombre() + "</b></td><td>" + hojas.get(i).getIdMetodo() + "</td><td>" + hojas.get(i).getSiguientes().toString() + "</td></tr>\n");
            }
            
            pw.append("</table>>];\n}");

            pw.close();

            try {
                Runtime.getRuntime().exec("dot -Tjpg Reportes/" + this.nombre + "Siguientes.txt -o Reportes/" + this.nombre + "Siguientes.jpg");
                //Runtime.getRuntime().exec("cmd /c start " + this.nombre + ".txt");
                //Runtime.getRuntime().exec("cmd /c start Reportes/" + this.nombre + "Siguientes.jpg");
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
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
