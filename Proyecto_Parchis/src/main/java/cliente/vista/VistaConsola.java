/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente.vista;

import java.util.Map;
/**
 *
 * @author Infraestructura
 */
public class VistaConsola {
    public void mostrarPosiciones(Map<String, Integer> posiciones) {
        System.out.println("=== Estado del juego ===");
        posiciones.forEach((jugador, pos) -> 
            System.out.println(jugador + " -> " + pos)
        );
        System.out.println("=======================");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
