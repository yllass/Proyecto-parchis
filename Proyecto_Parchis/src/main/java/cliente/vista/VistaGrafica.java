package cliente.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class VistaGrafica extends JFrame {

    private JLabel lblTurno;
    private JLabel lblDado;
    private JButton btnTirarDado;

    private JLabel lblJugador1;
    private JLabel lblJugador2;
    private JLabel lblJugador3;
    private JLabel lblJugador4;

    private JLabel posJugador1;
    private JLabel posJugador2;
    private JLabel posJugador3;
    private JLabel posJugador4;

    public VistaGrafica() {
        setTitle("Parchís - Cliente");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        getContentPane().setBackground(new Color(250, 250, 255));
        setLayout(new BorderLayout());

        // Panel superior: turno actual
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(200, 230, 255));
        lblTurno = new JLabel("Esperando jugadores...");
        lblTurno.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelSuperior.add(lblTurno);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central: jugadores y posiciones
        JPanel panelCentro = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panelCentro.setBackground(new Color(250, 250, 255));

        lblJugador1 = new JLabel("Jugador 1:");
        lblJugador2 = new JLabel("Jugador 2:");
        lblJugador3 = new JLabel("Jugador 3:");
        lblJugador4 = new JLabel("Jugador 4:");

        lblJugador1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblJugador2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblJugador3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblJugador4.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        posJugador1 = new JLabel("-");
        posJugador2 = new JLabel("-");
        posJugador3 = new JLabel("-");
        posJugador4 = new JLabel("-");

        posJugador1.setFont(new Font("Consolas", Font.BOLD, 14));
        posJugador2.setFont(new Font("Consolas", Font.BOLD, 14));
        posJugador3.setFont(new Font("Consolas", Font.BOLD, 14));
        posJugador4.setFont(new Font("Consolas", Font.BOLD, 14));

        panelCentro.add(lblJugador1);
        panelCentro.add(posJugador1);
        panelCentro.add(lblJugador2);
        panelCentro.add(posJugador2);
        panelCentro.add(lblJugador3);
        panelCentro.add(posJugador3);
        panelCentro.add(lblJugador4);
        panelCentro.add(posJugador4);

        add(panelCentro, BorderLayout.CENTER);

        // Panel inferior: dado y botón
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(230, 245, 255));

        lblDado = new JLabel("🎲 Dado: -");
        lblDado.setFont(new Font("Segoe UI", Font.BOLD, 16));

        btnTirarDado = new JButton("Tirar Dado");
        btnTirarDado.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnTirarDado.setBackground(new Color(100, 180, 255));
        btnTirarDado.setForeground(Color.WHITE);
        btnTirarDado.setFocusPainted(false);
        btnTirarDado.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));

        panelInferior.add(lblDado);
        panelInferior.add(btnTirarDado);
        add(panelInferior, BorderLayout.SOUTH);
    }

    /**
     * Muestra el estado actual del tablero (posiciones de los jugadores).
     */
    public void actualizarEstado(Map<String, Integer> posiciones, String turnoActual) {
        int i = 0;
        JLabel[] nombres = {lblJugador1, lblJugador2, lblJugador3, lblJugador4};
        JLabel[] posicionesLbl = {posJugador1, posJugador2, posJugador3, posJugador4};

        // Limpiar antes de mostrar
        for (JLabel lbl : nombres) lbl.setText("Jugador:");
        for (JLabel lbl : posicionesLbl) lbl.setText("-");

        for (Map.Entry<String, Integer> entry : posiciones.entrySet()) {
            if (i < 4) {
                nombres[i].setText(entry.getKey());
                posicionesLbl[i].setText(String.valueOf(entry.getValue()));
                i++;
            }
        }

        lblTurno.setText("Turno actual: " + turnoActual);
    }

    /**
     * Muestra el valor del dado tirado.
     */
    public void mostrarDado(int valor) {
        lblDado.setText("🎲 Dado: " + valor);
    }

    /**
     * Asigna la acción del botón para tirar el dado.
     */
    public void setAccionTirarDado(ActionListener accion) {
        btnTirarDado.addActionListener(accion);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}
