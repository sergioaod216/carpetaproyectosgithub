import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int CANTIDAD_CARTAS_POR_JUGADOR = 10;
    private final int MARGEN_CARTAS = 10;
    private final int DISTANCIA_ENTRE_CARTAS = 40;

    private Carta[] cartas = new Carta[CANTIDAD_CARTAS_POR_JUGADOR];
    private Random generadorAleatorio = new Random();

    private String[] nombresCartas = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private String[] nombresPintas = {"Trebol", "Pica", "Corazon", "Diamante"};
    private String[] nombresGrupos = {"", "", "Par", "Terna", "Cuarta", "Quinta", "Sexta", "Septima", "Octava", "Novena", "Decima"};

    public void repartir(int[] cantidadVecesUsada, int cantidadBarajas) {
        for (int indiceCarta = 0; indiceCarta < CANTIDAD_CARTAS_POR_JUGADOR; indiceCarta++) {
            cartas[indiceCarta] = new Carta(generadorAleatorio, cantidadVecesUsada, cantidadBarajas);
        }
    }

    public void mostrar(JPanel panelJugador) {
        panelJugador.removeAll();
        panelJugador.setLayout(null);

        int posicionHorizontal = MARGEN_CARTAS + CANTIDAD_CARTAS_POR_JUGADOR * DISTANCIA_ENTRE_CARTAS;

        for (int indiceCarta = 0; indiceCarta < CANTIDAD_CARTAS_POR_JUGADOR; indiceCarta++) {
            posicionHorizontal -= DISTANCIA_ENTRE_CARTAS;
            cartas[indiceCarta].mostrar(panelJugador, posicionHorizontal, MARGEN_CARTAS);
        }

        panelJugador.repaint();
    }

    public String getGrupos() {
        if (cartas[0] == null) {
            return "Primero debe repartir las cartas";
        }

        boolean[] cartaPerteneceAGrupo = new boolean[CANTIDAD_CARTAS_POR_JUGADOR];
        String resultado = "";
        boolean seEncontroGrupo = false;

        // 1. Buscar grupos por el mismo nombre: par, terna, cuarta, etc.
        int[] cantidadPorNombre = new int[13];

        for (int indiceCarta = 0; indiceCarta < CANTIDAD_CARTAS_POR_JUGADOR; indiceCarta++) {
            int posicionNombre = cartas[indiceCarta].getNombre().ordinal();
            cantidadPorNombre[posicionNombre]++;
        }

        for (int posicionNombre = 0; posicionNombre < cantidadPorNombre.length; posicionNombre++) {
            if (cantidadPorNombre[posicionNombre] >= 2) {
                resultado += nombresGrupos[cantidadPorNombre[posicionNombre]] + " de "
                        + nombresCartas[posicionNombre] + "\n";
                seEncontroGrupo = true;

                for (int indiceCarta = 0; indiceCarta < CANTIDAD_CARTAS_POR_JUGADOR; indiceCarta++) {
                    if (cartas[indiceCarta].getNombre().ordinal() == posicionNombre) {
                        cartaPerteneceAGrupo[indiceCarta] = true;
                    }
                }
            }
        }

        if (seEncontroGrupo) {
            resultado += "\n";
        }

        // 2. Buscar escaleras de la misma pinta
        int[][] cantidadPorPintaYNombre = new int[4][13];

        for (int indiceCarta = 0; indiceCarta < CANTIDAD_CARTAS_POR_JUGADOR; indiceCarta++) {
            int posicionPinta = cartas[indiceCarta].getPinta().ordinal();
            int posicionNombre = cartas[indiceCarta].getNombre().ordinal();
            cantidadPorPintaYNombre[posicionPinta][posicionNombre]++;
        }

        boolean seEncontroEscalera = false;

        for (int posicionPinta = 0; posicionPinta < 4; posicionPinta++) {
            int posicionNombre = 0;

            while (posicionNombre < 13) {
                if (cantidadPorPintaYNombre[posicionPinta][posicionNombre] > 0) {
                    int posicionInicioEscalera = posicionNombre;
                    int posicionFinEscalera = posicionNombre;

                    while (posicionFinEscalera + 1 < 13
                            && cantidadPorPintaYNombre[posicionPinta][posicionFinEscalera + 1] > 0) {
                        posicionFinEscalera++;
                    }

                    int cantidadCartasEscalera = posicionFinEscalera - posicionInicioEscalera + 1;

                    if (cantidadCartasEscalera >= 2) {
                        resultado += nombresGrupos[cantidadCartasEscalera] + " de "
                                + nombresPintas[posicionPinta] + " de "
                                + nombresCartas[posicionInicioEscalera] + " a "
                                + nombresCartas[posicionFinEscalera] + "\n";

                        seEncontroEscalera = true;
                        seEncontroGrupo = true;

                        for (int indiceCarta = 0; indiceCarta < CANTIDAD_CARTAS_POR_JUGADOR; indiceCarta++) {
                            int pintaCarta = cartas[indiceCarta].getPinta().ordinal();
                            int nombreCarta = cartas[indiceCarta].getNombre().ordinal();

                            if (pintaCarta == posicionPinta
                                    && nombreCarta >= posicionInicioEscalera
                                    && nombreCarta <= posicionFinEscalera) {
                                cartaPerteneceAGrupo[indiceCarta] = true;
                            }
                        }
                    }

                    posicionNombre = posicionFinEscalera + 1;
                } else {
                    posicionNombre++;
                }
            }
        }

        if (!seEncontroGrupo) {
            resultado = "No se encontraron grupos\n";
        }

        if (seEncontroEscalera) {
            resultado += "\n";
        }

        // 3. Mostrar las cartas sobrantes y calcular sus puntos
        resultado += "Sobran:\n";
        int puntaje = 0;
        boolean hayCartasSobrantes = false;

        for (int indiceCarta = 0; indiceCarta < CANTIDAD_CARTAS_POR_JUGADOR; indiceCarta++) {
            if (!cartaPerteneceAGrupo[indiceCarta]) {
                int posicionNombre = cartas[indiceCarta].getNombre().ordinal();
                int posicionPinta = cartas[indiceCarta].getPinta().ordinal();

                resultado += nombresCartas[posicionNombre] + " de " + nombresPintas[posicionPinta] + "\n";
                puntaje += cartas[indiceCarta].getValor();
                hayCartasSobrantes = true;
            }
        }

        if (!hayCartasSobrantes) {
            resultado += "Ninguna\n";
        }

        resultado += "\nPuntos:\n" + puntaje;

        return resultado;
    }
}
