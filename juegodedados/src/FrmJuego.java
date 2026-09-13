import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FrmJuego extends JFrame {

    // variables y objetos globales
    private JLabel lblDado1, lblDado2, lblLanzamientos, lblCenas;
    private JButton btnLanzar;

    private Dado dado1 = new Dado();
    private Dado dado2 = new Dado();

    private int lanzamientos, cenas;

    public FrmJuego() {

        setSize(500, 300); // Tamaño de la ventana
        setTitle("Juguemos a los dados"); // Título
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar programa
        setLayout(null); // Posicionar elementos manualmente
        setLocationRelativeTo(null); // Centrar ventana

        // cargar una imagen que corresponda a una de las caras de los dados
        String rutaImagen = "/imagendados/3.jpeg"; // Imagen inicial
        ImageIcon imgDado = new ImageIcon(getClass().getResource(rutaImagen)); // Cargar imagen

        lblDado1 = new JLabel(imgDado); // Primer dado
        lblDado1.setBounds(10, 10, imgDado.getIconWidth(), imgDado.getIconHeight()); // Posición
        add(lblDado1); // Agregar dado

        lblDado2 = new JLabel(imgDado); // Segundo dado
        lblDado2.setBounds(20 + imgDado.getIconWidth(), 10,
                imgDado.getIconWidth(), imgDado.getIconHeight()); // Posición
        add(lblDado2); // Agregar dado

        JLabel lblTituloLanzamientos = new JLabel("Lanzamientos"); // Título lanzamientos
        lblTituloLanzamientos.setBounds(30 + 2 * imgDado.getIconWidth(), 10, 100, 25); // Posición
        lblTituloLanzamientos.setHorizontalAlignment(JLabel.CENTER); // Centrar texto
        add(lblTituloLanzamientos); // Agregar título

        JLabel lblTituloCenas = new JLabel("Cenas"); // Título cenas
        lblTituloCenas.setBounds(140 + 2 * imgDado.getIconWidth(), 10, 100, 25); // Posición
        lblTituloCenas.setHorizontalAlignment(JLabel.CENTER); // Centrar texto
        add(lblTituloCenas); // Agregar título

        lblLanzamientos = new JLabel("0"); // Contador lanzamientos
        lblLanzamientos.setBounds(30 + 2 * imgDado.getIconWidth(), 45, 100, 100); // Posición
        lblLanzamientos.setFont(new Font("Impact", Font.BOLD, 72)); // Fuente
        lblLanzamientos.setHorizontalAlignment(JLabel.RIGHT); // Alinear texto
        lblLanzamientos.setBackground(new Color(0, 0, 0)); // Fondo negro
        lblLanzamientos.setForeground(new Color(0, 255, 0)); // Texto verde
        lblLanzamientos.setOpaque(true); // Mostrar fondo
        add(lblLanzamientos); // Agregar contador

        lblCenas = new JLabel("0"); // Contador cenas
        lblCenas.setBounds(140 + 2 * imgDado.getIconWidth(), 45, 100, 100); // Posición
        lblCenas.setFont(new Font("Impact", Font.BOLD, 72)); // Fuente
        lblCenas.setHorizontalAlignment(JLabel.RIGHT); // Alinear texto
        lblCenas.setBackground(new Color(0, 0, 0)); // Fondo negro
        lblCenas.setForeground(new Color(0, 255, 0)); // Texto verde
        lblCenas.setOpaque(true); // Mostrar fondo
        add(lblCenas); // Agregar contador

        JButton btnIniciar = new JButton("Iniciar"); // Botón iniciar
        btnIniciar.setBounds(10, 20 + imgDado.getIconHeight(), 100, 25); // Posición
        add(btnIniciar); // Agregar botón

        btnLanzar = new JButton("Lanzar"); // Botón lanzar
        btnLanzar.setBounds(120, 20 + imgDado.getIconHeight(), 100, 25); // Posición
        btnLanzar.setEnabled(false); // Comienza deshabilitado
        add(btnLanzar); // Agregar botón

        // eventos
        btnIniciar.addActionListener(evento -> {
            iniciarLanzamientos(); // Comenzar nuevo jugador
        });

        btnLanzar.addActionListener(evento -> {
            realizarLanzamiento(); // Lanzar dados
        });
    }

    private void iniciarLanzamientos() {

        lanzamientos = 0; // Reiniciar lanzamientos
        lblLanzamientos.setText("0"); // Mostrar cero

        cenas = 0; // Reiniciar cenas
        lblCenas.setText("0"); // Mostrar cero

        btnLanzar.setEnabled(true); // Habilitar botón lanzar
    }

    private void realizarLanzamiento() {

        // Solo permitir máximo 20 lanzamientos
        if (lanzamientos < 20) {

            // lanzar dados
            dado1.lanzar();
            dado2.lanzar();

            // mostrar dados
            dado1.mostrar(lblDado1);
            dado2.mostrar(lblDado2);

            // contar lanzamientos
            lanzamientos++;
            lblLanzamientos.setText(String.valueOf(lanzamientos));

            // contar cenas
            if (dado1.getNumero() + dado2.getNumero() >= 11) {
                cenas++;
            }

            // mostrar cantidad de cenas
            lblCenas.setText(String.valueOf(cenas));

            // terminar después de 20 lanzamientos
            if (lanzamientos == 20) {

                btnLanzar.setEnabled(false); // Deshabilitar botón

                JOptionPane.showMessageDialog(
                        null,
                        "Terminó el turno\n" +
                        "Lanzamientos: " + lanzamientos +
                        "\nCenas: " + cenas +
                        "\nPresione Iniciar para el siguiente jugador"
                );
            }
        }
    }
}
