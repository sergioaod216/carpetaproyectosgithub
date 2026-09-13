import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Carta {

    private int indice; // identifica la carta con un numero entre 1 y 52

    // Constructor: genera una carta respetando la cantidad de barajas disponibles
    public Carta(Random generadorAleatorio, int[] cantidadVecesUsada, int cantidadBarajas) {
        boolean cartaDisponible = false;

        while (!cartaDisponible) {
            int indiceGenerado = generadorAleatorio.nextInt(52) + 1;

            if (cantidadVecesUsada[indiceGenerado - 1] < cantidadBarajas) {
                indice = indiceGenerado;
                cantidadVecesUsada[indiceGenerado - 1]++;
                cartaDisponible = true;
            }
        }
    }

    public void mostrar(JPanel panelCartas, int posicionHorizontal, int posicionVertical) {
        String rutaImagen = "imagenes/CARTA" + indice + ".JPG";
        ImageIcon imagenCarta = new ImageIcon(getClass().getResource(rutaImagen));

        JLabel etiquetaCarta = new JLabel(imagenCarta);
        etiquetaCarta.setBounds(posicionHorizontal, posicionVertical,
                imagenCarta.getIconWidth(), imagenCarta.getIconHeight());
        panelCartas.add(etiquetaCarta);

        etiquetaCarta.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evento) {
                JOptionPane.showMessageDialog(null, getNombre() + " de " + getPinta());
            }
        });
    }

    public Pinta getPinta() {
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

    public NombreCarta getNombre() {
        int numeroDentroDePinta = indice % 13;

        if (numeroDentroDePinta == 0) {
            numeroDentroDePinta = 13;
        }

        return NombreCarta.values()[numeroDentroDePinta - 1];
    }

    public int getValor() {
        int posicionNombre = getNombre().ordinal();

        // As, 10, Jack, Queen y King valen 10. Las demas valen su numero.
        if (posicionNombre == 0 || posicionNombre >= 9) {
            return 10;
        }

        return posicionNombre + 1;
    }
}
