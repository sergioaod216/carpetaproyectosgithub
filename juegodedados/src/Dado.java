import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Dado {

    private int numero; // Guarda el número obtenido
    private Random r = new Random(); // Generador de números aleatorios

    public void lanzar() { // Simula el lanzamiento del dado
        numero = r.nextInt(6) + 1; // Genera un número entre 1 y 6
    }

    public void mostrar(JLabel lblDado) { // Muestra la imagen correspondiente
        String rutaImagen = "/imagendados/" + numero + ".jpeg"; // Ruta de la imagen
        ImageIcon imgDado = new ImageIcon(getClass().getResource(rutaImagen)); // Cargar imagen
        lblDado.setIcon(imgDado); // Mostrar imagen en el JLabel
    }

    // Getter: devuelve el número obtenido
    public int getNumero() {
        return numero;
    }
}
