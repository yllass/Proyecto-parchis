/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cliente;

import cliente.controlador.ControladorCliente;
import cliente.modelo.ModeloJugador;
import cliente.vista.VistaGrafica;
/**
 *
 * @author Infraestructura
 */
public class ClienteMain {
    public static void main(String[] args) {
        ModeloJugador modelo = new ModeloJugador();
        VistaGrafica vista = new VistaGrafica();
        ControladorCliente controlador = new ControladorCliente(modelo, vista);

        controlador.iniciarCliente();
    }
}
