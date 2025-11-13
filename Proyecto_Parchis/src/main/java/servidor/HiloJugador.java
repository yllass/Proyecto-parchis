package servidor;

import java.io.*;
import java.net.Socket;

public class HiloJugador extends Thread {

    private Socket socket;
    private String nombre;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Blackboard blackboard;
    private ServidorParchis servidor;

    public HiloJugador(Socket socket, String nombre, ObjectInputStream in, ObjectOutputStream out, Blackboard blackboard, ServidorParchis servidor) {
        this.socket = socket;
        this.nombre = nombre;
        this.in = in;
        this.out = out;
        this.blackboard = blackboard;
        this.servidor = servidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void enviar(Object mensaje) throws IOException {
        out.writeObject(mensaje);
        out.flush();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Object recibido = in.readObject();
                if (recibido instanceof Integer) {
                    int nuevaPos = (Integer) recibido;
                    blackboard.actualizarPosicion(nombre, nuevaPos);
                }
            }
        } catch (Exception e) {
            System.out.println("Jugador desconectado: " + nombre);
        }
    }
}
