import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Programa para  realizar calculos de la ecuacion lineal");

        // declarar variables
        double x1, y2, x2, y1;

        // crear objeto de la clase Scanner
        Scanner lector = new Scanner(System.in);

        // leer las variables de entrada
        System.out.println("coordenadas del primer punto");
        System.out.println("x1: ");
        x1 = lector.nextDouble();
        System.out.println("y1: ");
        y1 = lector.nextDouble();

        System.out.println("coordenadas del segundo punto");
        System.out.println("x2: ");
        x2 = lector.nextDouble();
        System.out.println("y2: ");
        y2 = lector.nextDouble();

        // proceso
        double distancia = Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
        double pendiente = (y2 - y1) / (x2 - x1);
        double interseccion = y1 - pendiente * x1;
        String ecuacion = "y = " + pendiente + "x + ";
        if (interseccion < 0) {
            ecuacion += interseccion; // ecuacion = ecuacion + interseccion
        } else if (interseccion > 0) {
            ecuacion += "+" + interseccion;
        }

        // mostrar resultados
        System.out.println("La distancia entre los puntos es: " + distancia);
        System.out.println("La pendiente de la recta es: " + pendiente);
        System.out.println("La intersección con el eje y es: " + interseccion);
        System.out.println("La ecuacion de la recta es: " + ecuacion);

        // cerrar lector de variables
        lector.close();

    }

}
