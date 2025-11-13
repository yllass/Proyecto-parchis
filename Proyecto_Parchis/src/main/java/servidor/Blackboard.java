package servidor;

import java.io.IOException;
import java.util.*;

public class Blackboard {

    private Map<String, Integer> posiciones = new HashMap<>();
    private List<HiloJugador> jugadores;
    private int turnoActual = 0;

    public void iniciarPartida(List<HiloJugador> jugadores) {
        this.jugadores = jugadores;
        posiciones.clear();
        for (HiloJugador j : jugadores) {
            posiciones.put(j.getNombre(), 0);
        }
        notificarEstado();
    }

    public synchronized void actualizarPosicion(String jugador, int nuevaPos) {
        posiciones.put(jugador, nuevaPos);
        avanzarTurno();
        notificarEstado();
    }

    private void avanzarTurno() {
        turnoActual = (turnoActual + 1) % jugadores.size();
    }

    public String getJugadorEnTurno() {
        return jugadores.get(turnoActual).getNombre();
    }

    public synchronized void notificarEstado() {
        Map<String, Object> estado = new HashMap<>();
        estado.put("posiciones", new HashMap<>(posiciones));
        estado.put("turno", getJugadorEnTurno());

        for (HiloJugador j : jugadores) {
            try {
                j.enviar(estado);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
