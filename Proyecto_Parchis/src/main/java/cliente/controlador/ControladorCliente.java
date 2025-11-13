package cliente.controlador;

import cliente.modelo.ModeloJugador;
import cliente.vista.VistaGrafica;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ControladorCliente {

    private ModeloJugador modelo;
    private VistaGrafica vista;
    private static final int PUERTO = 12345;
    private String nombreJugador;
    private String turnoActual;

    public ControladorCliente(ModeloJugador modelo, VistaGrafica vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciarCliente() {
        SwingUtilities.invokeLater(() -> vista.setVisible(true));

        try {
            String host = JOptionPane.showInputDialog("Ingrese la IP del servidor:");
            Socket socket = new Socket(host, PUERTO);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            nombreJugador = JOptionPane.showInputDialog("Ingrese su nombre de jugador:");
            out.writeObject(nombreJugador);
            out.flush();

            vista.setAccionTirarDado(e -> {
                if (!nombreJugador.equals(turnoActual)) {
                    vista.mostrarMensaje("No es tu turno todavía.");
                    return;
                }
                int dado = (int) (Math.random() * 6 + 1);
                vista.mostrarDado(dado);
                int nuevaPos = modelo.getPosiciones().getOrDefault(nombreJugador, 0) + dado;

                try {
                    out.writeObject(nuevaPos);
                    out.flush();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });

            new Thread(() -> {
                try {
                    while (true) {
                        Object recibido = in.readObject();
                        if (recibido instanceof Map) {
                            Map<String, Object> estado = (Map<String, Object>) recibido;
                            Map<String, Integer> posiciones = (Map<String, Integer>) estado.get("posiciones");
                            turnoActual = (String) estado.get("turno");

                            modelo.actualizarPosiciones(posiciones);
                            SwingUtilities.invokeLater(() -> vista.actualizarEstado(posiciones, turnoActual));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error de conexión con el servidor.");
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al conectar con el servidor.");
        }
    }
}
