package servidor;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServidorParchis {

    private static final int PUERTO = 12345;
    private static final int MIN_JUGADORES = 2;
    private static final int MAX_JUGADORES = 2; // Solo 2 jugadores

    private ServerSocket servidor;
    private Blackboard blackboard;
    private List<HiloJugador> jugadores;

    public ServidorParchis() throws IOException {
        servidor = new ServerSocket(PUERTO);
        blackboard = new Blackboard();
        jugadores = new ArrayList<>();
        System.out.println("🟢 Servidor Parchís iniciado en puerto " + PUERTO);
    }

    public void iniciar() {
        try {
            while (true) {
                System.out.println("Esperando jugadores... (" + jugadores.size() + "/" + MAX_JUGADORES + ")");
                Socket socket = servidor.accept();

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                String nombreJugador = (String) in.readObject();
                System.out.println("Jugador conectado: " + nombreJugador);

                if (jugadores.size() >= MAX_JUGADORES) {
                    out.writeObject("Servidor lleno. Máximo " + MAX_JUGADORES + " jugadores.");
                    out.flush();
                    socket.close();
                    continue;
                }

                HiloJugador hilo = new HiloJugador(socket, nombreJugador, in, out, blackboard, this);
                jugadores.add(hilo);
                hilo.start();

                if (jugadores.size() == MIN_JUGADORES) {
                    System.out.println("🎲 Iniciando partida con " + jugadores.size() + " jugadores.");
                    blackboard.iniciarPartida(jugadores);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void notificarTodos(Object mensaje) {
        for (HiloJugador jugador : jugadores) {
            try {
                jugador.enviar(mensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ServidorParchis servidor = new ServidorParchis();
            servidor.iniciar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
