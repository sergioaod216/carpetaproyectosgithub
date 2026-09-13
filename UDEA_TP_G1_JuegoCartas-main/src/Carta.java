import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Carta {

    private int indice; // numero de la carta entre 1 y 52

    // constructor: genera una carta sin pasar el limite de barajas
    public Carta(Random r, int[] cartasUsadas, int cantidadBarajas) {
        boolean disponible = false;

        while (!disponible) {
            int numero = r.nextInt(52) + 1; // generar numero entre 1 y 52

            if (cartasUsadas[numero - 1] < cantidadBarajas) { // verificar disponibilidad
                indice = numero; // guardar carta
                cartasUsadas[numero - 1]++; // contar cuantas veces ha salido
                disponible = true; // terminar busqueda
            }
        }
    }

    public void mostrar(JPanel pnl, int x, int y) {
        String rutaImagen = "imagenes/CARTA" + indice + ".JPG"; // ruta de imagen
        ImageIcon imgCarta = new ImageIcon(getClass().getResource(rutaImagen)); // cargar imagen

        JLabel lblCarta = new JLabel(imgCarta); // crear etiqueta con imagen
        lblCarta.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight()); // ubicar carta
        pnl.add(lblCarta); // agregar carta al panel

        // mostrar nombre y pinta al hacer clic
        lblCarta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evento) {
                JOptionPane.showMessageDialog(null, getNombre() + " de " + getPinta());
            }
        });
    }

    public Pinta getPinta() { // obtener pinta segun el indice
        if (indice <= 13) {
            return Pinta.TREBOL;
        } else if (indice <= 26) {
            return Pinta.PICA;
        } else if (indice <= 39) {
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    public NombreCarta getNombre() { // obtener nombre segun el indice
        int residuo = indice % 13;
        if (residuo == 0) {
            residuo = 13;
        }
        return NombreCarta.values()[residuo - 1];
    }

    public int getValor() { // valor de la carta para calcular los puntos
        int posicion = getNombre().ordinal();

        if (posicion == 0 || posicion >= 9) { // As, 10, J, Q y K valen 10
            return 10;
        }

        return posicion + 1; // 2 hasta 9 valen su numero
    }
}
