import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private final int TOTAL_CARTAS = 10;
    private final int MARGEN = 10;
    private final int DISTANCIA = 40;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random();

    // textos usados para mostrar resultados de forma mas clara
    private String[] nombres = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private String[] pintas = {"Trebol", "Pica", "Corazon", "Diamante"};
    private String[] nombresGrupo = {"", "", "Par", "Terna", "Cuarta", "Quinta", "Sexta", "Septima", "Octava", "Novena", "Decima"};

    public void repartir(int[] cartasUsadas, int cantidadBarajas) {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r, cartasUsadas, cantidadBarajas); // crear cada carta
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll(); // limpiar cartas anteriores
        pnl.setLayout(null); // trabajar con posiciones

        int posicion = MARGEN + TOTAL_CARTAS * DISTANCIA;

        for (int i = 0; i < TOTAL_CARTAS; i++) {
            posicion -= DISTANCIA;
            cartas[i].mostrar(pnl, posicion, MARGEN); // mostrar carta
        }

        pnl.repaint(); // redibujar panel
    }

    public String getGrupos() {

        if (cartas[0] == null) { // evitar verificar antes de repartir
            return "Primero debe repartir las cartas";
        }

        boolean[] cartaEnGrupo = new boolean[TOTAL_CARTAS]; // indica si una carta pertenece a algun grupo
        String resultado = "";
        boolean hayGrupo = false;

        // =====================================================
        // 1. GRUPOS POR EL MISMO NOMBRE: par, terna, cuarta...
        // =====================================================
        int[] contadores = new int[13]; // un contador para cada nombre de carta

        for (int i = 0; i < TOTAL_CARTAS; i++) {
            int posicionNombre = cartas[i].getNombre().ordinal();
            contadores[posicionNombre]++;
        }

        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) { // desde par se considera grupo
                resultado += nombresGrupo[contadores[i]] + " de " + nombres[i] + "\n";
                hayGrupo = true;

                // marcar las cartas que forman este grupo
                for (int j = 0; j < TOTAL_CARTAS; j++) {
                    if (cartas[j].getNombre().ordinal() == i) {
                        cartaEnGrupo[j] = true;
                    }
                }
            }
        }

        if (hayGrupo) {
            resultado += "\n";
        }

        // =====================================================
        // 2. ESCALERAS DE LA MISMA PINTA
        // =====================================================
        int[][] presentes = new int[4][13]; // filas=pintas, columnas=nombres

        for (int i = 0; i < TOTAL_CARTAS; i++) {
            int pinta = cartas[i].getPinta().ordinal();
            int nombre = cartas[i].getNombre().ordinal();
            presentes[pinta][nombre]++;
        }

        boolean hayEscalera = false;

        for (int p = 0; p < 4; p++) { // recorrer cada pinta
            int n = 0;

            while (n < 13) {
                if (presentes[p][n] > 0) {
                    int inicio = n;
                    int fin = n;

                    // buscar hasta donde llega la secuencia
                    while (fin + 1 < 13 && presentes[p][fin + 1] > 0) {
                        fin++;
                    }

                    int cantidad = fin - inicio + 1;

                    if (cantidad >= 2) { // par, terna, cuarta... en escalera
                        resultado += nombresGrupo[cantidad] + " de " + pintas[p] + " de " + nombres[inicio] + " a " + nombres[fin] + "\n";
                        hayEscalera = true;
                        hayGrupo = true;

                        // marcar todas las cartas que estan dentro de la escalera
                        for (int i = 0; i < TOTAL_CARTAS; i++) {
                            int pintaCarta = cartas[i].getPinta().ordinal();
                            int nombreCarta = cartas[i].getNombre().ordinal();

                            if (pintaCarta == p && nombreCarta >= inicio && nombreCarta <= fin) {
                                cartaEnGrupo[i] = true;
                            }
                        }
                    }

                    n = fin + 1; // continuar despues de la secuencia encontrada
                } else {
                    n++;
                }
            }
        }

        if (!hayGrupo) {
            resultado = "No se encontraron grupos\n";
        }

        if (hayEscalera) {
            resultado += "\n";
        }

        // =====================================================
        // 3. CARTAS SOBRANTES Y PUNTAJE
        // =====================================================
        resultado += "Sobran:\n";
        int puntos = 0;
        boolean haySobrantes = false;

        for (int i = 0; i < TOTAL_CARTAS; i++) {
            if (!cartaEnGrupo[i]) {
                int nombre = cartas[i].getNombre().ordinal();
                int pinta = cartas[i].getPinta().ordinal();

                resultado += nombres[nombre] + " de " + pintas[pinta] + "\n";
                puntos += cartas[i].getValor();
                haySobrantes = true;
            }
        }

        if (!haySobrantes) {
            resultado += "Ninguna\n";
        }

        resultado += "\nPuntos:\n" + puntos;

        return resultado;
    }
}
