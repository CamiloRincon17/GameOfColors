/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.juegodecolores;

/**
 *
 * @author URIEL MAURICIO
 */
public class JuegoDeColores {
    
    // Resultado del juego: separa la lógica de los mensajes
    public enum Resultado {
        NUEVO_COLOR,      // Se agregó un color, ahora hay que repetir
        ACIERTO_PARCIAL,  // Acertó pero falta más
        SECUENCIA_COMPLETA, // Completó toda la secuencia
        PERDISTE,           // Se equivocó
        HOLA
    }

    // Lista doblemente enlazada
    NodoDoble inicio;
    
    // Variables de estado del juego
    private boolean modoCrear = true;
    private NodoDoble nodoActual = null;
    
    // Sistema de puntaje
    private int puntaje = 0;
    private int mejorPuntaje = 0;
    private int ronda = 0;
    private int aciertosEnRonda = 0;
    
    // Datos del último resultado (para que la UI lea)
    private String colorEsperado = "";
    private int ultimosPuntosGanados = 0;
    private int puntajeFinal = 0;       // puntaje al momento de perder
    private int rondasCompletadas = 0;   // rondas completadas al perder

    // Agregar nodo al final de la lista doblemente enlazada
    public void crearFinal(String color) {
        NodoDoble nuevo = new NodoDoble(color);
        if (inicio == null) {
            inicio = nuevo;
        } else {
            // Recorrer hasta el último nodo
            NodoDoble temporal = inicio;
            while (temporal.getSiguiente() != null) {
                temporal = temporal.getSiguiente();
            }
            // Enlazar: doble enlace
            temporal.setSiguiente(nuevo);
            nuevo.setAnterior(temporal);
        }
    }

    // Reiniciar el estado del juego
    private void reiniciar() {
        inicio = null;
        modoCrear = true;
        nodoActual = null;
        puntaje = 0;
        ronda = 0;
        aciertosEnRonda = 0;
    }

    // Getters para que la UI arme los mensajes
    public int getPuntaje() { return puntaje; }
    public int getMejorPuntaje() { return mejorPuntaje; }
    public int getRonda() { return ronda; }
    public int getAciertosEnRonda() { return aciertosEnRonda; }
    public String getColorEsperado() { return colorEsperado; }
    public int getUltimosPuntosGanados() { return ultimosPuntosGanados; }
    public int getPuntajeFinal() { return puntajeFinal; }
    public int getRondasCompletadas() { return rondasCompletadas; }

    // Lógica principal del juego — retorna QUÉ PASÓ, no un mensaje
    public Resultado procesarJuego(String color) {
        if (modoCrear) {
            // FASE DE CREACIÓN: agregar color a la lista doblemente enlazada
            crearFinal(color);
            ronda++;
            aciertosEnRonda = 0;
            modoCrear = false;
            nodoActual = inicio;
            return Resultado.NUEVO_COLOR;

        } else {
            // FASE DE REPETICIÓN
            if (nodoActual != null && nodoActual.getColor().equalsIgnoreCase(color)) {
                aciertosEnRonda++;
                nodoActual = nodoActual.getSiguiente();

                if (nodoActual == null) {
                    // Completó toda la secuencia
                    ultimosPuntosGanados = 10 * ronda;
                    puntaje += ultimosPuntosGanados;
                    modoCrear = true;
                    return Resultado.SECUENCIA_COMPLETA;
                } else {
                    return Resultado.ACIERTO_PARCIAL;
                }

            } else {
                // Error
                colorEsperado = (nodoActual != null) ? nodoActual.getColor().toUpperCase() : "???";
                if (puntaje > mejorPuntaje) {
                    mejorPuntaje = puntaje;
                }
                puntajeFinal = puntaje;
                rondasCompletadas = ronda > 0 ? ronda - 1 : 0;
                reiniciar();
                return Resultado.PERDISTE;
            }
        }
    }
}

