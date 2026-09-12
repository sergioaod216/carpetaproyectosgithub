import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FrmJuego extends JFrame {

    // Variables globales
    private JLabel lblDado1;
    private JLabel lblDado2;
    private JLabel lblLanzamientos;
    private JLabel lblCenas;
    private JButton btnLanzar;

    public FrmJuego() {

        setTitle("Juego de Dados"); // Título de la ventana
        setSize(500, 300); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar programa
        setLocationRelativeTo(null); // Centrar ventana
        setLayout(null); // Permitir posiciones manuales

        // Cargar imagen del dado
        String rutaImagen = "/imagendados/3.jpeg"; // Ruta de la imagen
        ImageIcon imagenDado = new ImageIcon(getClass().getResource(rutaImagen)); // Cargar imagen

        lblDado1 = new JLabel(imagenDado); // Primer dado
        lblDado1.setBounds(10, 10, imagenDado.getIconWidth(), imagenDado.getIconHeight()); // Posición
        add(lblDado1); // Agregar primer dado

        lblDado2 = new JLabel(imagenDado); // Segundo dado
        lblDado2.setBounds(20 + imagenDado.getIconWidth(), 10,
                imagenDado.getIconWidth(), imagenDado.getIconHeight()); // Posición
        add(lblDado2); // Agregar segundo dado

        JLabel lblTituloLanzamientos = new JLabel("Lanzamientos"); // Título lanzamientos
        lblTituloLanzamientos.setBounds(30 + 2 * imagenDado.getIconWidth(), 10, 100, 25); // Posición
        lblTituloLanzamientos.setHorizontalAlignment(JLabel.CENTER); // Centrar texto
        add(lblTituloLanzamientos); // Agregar título

        JLabel lblTituloCenas = new JLabel("Cenas"); // Título cenas
        lblTituloCenas.setBounds(140 + 2 * imagenDado.getIconWidth(), 10, 100, 25); // Posición
        lblTituloCenas.setHorizontalAlignment(JLabel.CENTER); // Centrar texto
        add(lblTituloCenas); // Agregar título

        lblLanzamientos = new JLabel("0"); // Contador de lanzamientos
        lblLanzamientos.setBounds(30 + 2 * imagenDado.getIconWidth(), 45, 100, 100); // Posición
        lblLanzamientos.setFont(new Font("Impact", Font.BOLD, 72)); // Fuente
        lblLanzamientos.setHorizontalAlignment(JLabel.RIGHT); // Alinear texto
        lblLanzamientos.setBackground(new Color(0, 0, 0)); // Fondo negro
        lblLanzamientos.setForeground(new Color(0, 255, 0)); // Texto verde
        lblLanzamientos.setOpaque(true); // Mostrar fondo
        add(lblLanzamientos); // Agregar contador

        lblCenas = new JLabel("0"); // Contador de cenas
        lblCenas.setBounds(140 + 2 * imagenDado.getIconWidth(), 45, 100, 100); // Posición
        lblCenas.setFont(new Font("Impact", Font.BOLD, 72)); // Fuente
        lblCenas.setHorizontalAlignment(JLabel.RIGHT); // Alinear texto
        lblCenas.setBackground(new Color(0, 0, 0)); // Fondo negro
        lblCenas.setForeground(new Color(0, 255, 0)); // Texto verde
        lblCenas.setOpaque(true); // Mostrar fondo
        add(lblCenas); // Agregar contador

        JButton btnIniciar = new JButton("Iniciar"); // Crear botón iniciar
        btnIniciar.setBounds(10, 55 + imagenDado.getIconHeight(), 100, 25); // Posición
        add(btnIniciar); // Agregar botón

        btnLanzar = new JButton("Lanzar"); // Crear botón lanzar
        btnLanzar.setBounds(120, 55 + imagenDado.getIconHeight(), 100, 25); // Posición diferente
        btnLanzar.setEnabled(false); // Inicia deshabilitado
        add(btnLanzar); // Agregar botón

        // Eventos de los botones
        btnIniciar.addActionListener(e -> {
            iniciarLanzamientos(); // Iniciar juego
        });

        btnLanzar.addActionListener(e -> {
            realizarLanzamiento(); // Lanzar dados
        });
    }

    private void iniciarLanzamientos() {

    }

    private void realizarLanzamiento() {

    }
}


