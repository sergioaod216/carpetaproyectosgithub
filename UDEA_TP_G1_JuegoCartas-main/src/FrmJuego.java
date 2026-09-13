import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class FrmJuego extends JFrame {

    private JPanel pnlJugador1, pnlJugador2;
    private JTabbedPane tpJugadores;
    private JTextField txtBarajas;

    private Jugador jugador1 = new Jugador();
    private Jugador jugador2 = new Jugador();

    public FrmJuego() {
        setSize(560, 330); // tamaño ventana
        setTitle("Juego de Cartas - Punto 4"); // titulo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // cerrar programa
        setLayout(null); // posiciones manuales
        setLocationRelativeTo(null); // centrar ventana

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        add(btnVerificar);

        JLabel lblBarajas = new JLabel("Barajas:"); // cantidad de barajas disponibles
        lblBarajas.setBounds(250, 10, 60, 25);
        add(lblBarajas);

        txtBarajas = new JTextField("1"); // por defecto una baraja
        txtBarajas.setBounds(310, 10, 50, 25);
        add(txtBarajas);

        tpJugadores = new JTabbedPane(); // pestañas de jugadores
        tpJugadores.setBounds(10, 45, 530, 235);
        add(tpJugadores);

        pnlJugador1 = new JPanel();
        pnlJugador1.setBackground(new Color(50, 205, 50)); // verde mesa
        tpJugadores.add("Jugador 1", pnlJugador1);

        pnlJugador2 = new JPanel();
        pnlJugador2.setBackground(new Color(60, 180, 210)); // azul suave
        tpJugadores.add("Jugador 2", pnlJugador2);

        btnRepartir.addActionListener(evento -> {
            repartir();
        });

        btnVerificar.addActionListener(evento -> {
            verificar();
        });
    }

    private void repartir() {
        int cantidadBarajas;

        try {
            cantidadBarajas = Integer.parseInt(txtBarajas.getText()); // leer cantidad

            if (cantidadBarajas <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad de barajas debe ser mayor que 0");
                return;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Digite una cantidad numerica de barajas");
            txtBarajas.setText("1");
            txtBarajas.requestFocus();
            return;
        }

        int[] cartasUsadas = new int[52]; // contar cuantas veces aparece cada carta

        jugador1.repartir(cartasUsadas, cantidadBarajas); // repartir jugador 1
        jugador2.repartir(cartasUsadas, cantidadBarajas); // repartir jugador 2 con el mismo control

        jugador1.mostrar(pnlJugador1); // mostrar cartas jugador 1
        jugador2.mostrar(pnlJugador2); // mostrar cartas jugador 2
    }

    private void verificar() {
        String mensaje = "";

        switch (tpJugadores.getSelectedIndex()) {
            case 0:
                mensaje = jugador1.getGrupos();
                break;
            case 1:
                mensaje = jugador2.getGrupos();
                break;
        }

        if (!mensaje.isEmpty()) {
            JOptionPane.showMessageDialog(null, mensaje); // mostrar grupos, sobrantes y puntos
        }
    }
}
