/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juegodecolores;

/**
 *
 * @author URIEL MAURICIO
 */
public class NodoDoble {
    NodoDoble anterior;
    NodoDoble siguiente;
    String color;

    // Constructor por defecto
    public NodoDoble() {}

    // Constructor con color (simplifica la creación de nodos)
    public NodoDoble(String color) {
        this.color = color;
    }

    public NodoDoble getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }

    public NodoDoble getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
