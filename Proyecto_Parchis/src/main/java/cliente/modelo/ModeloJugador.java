/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente.modelo;

import java.util.Map;
/**
 *
 * @author Infraestructura
 */
public class ModeloJugador {
    private Map<String, Integer> posiciones;

    public void actualizarPosiciones(Map<String, Integer> posiciones) {
        this.posiciones = posiciones;
    }

    public Map<String, Integer> getPosiciones() {
        return posiciones;
    }
}
