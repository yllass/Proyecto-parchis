/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Infraestructura
 */
public class Dispatcher {
    private List<ObjectOutputStream> clientes = new ArrayList<>();

    public synchronized void registrarCliente(ObjectOutputStream out) {
        clientes.add(out);
    }

    public synchronized void notificarTodos(Object data) {
        for (ObjectOutputStream out : clientes) {
            try {
                out.writeObject(data);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
