import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Programa para realizar calculos de la ecuacion lineal");

        // declarar las variables
        double x1, y1, x2, y2;

        // declarar el objeto para lectura de variables

        Scanner lector = new Scanner(System.in);

        // leer las variables de entrada

        System.out.println("coordenadas del primer punto:");
        System.out.print("x: ");
        x1 = lector.nextDouble();
        System.out.print("y: ");
        y1 = lector.nextDouble();

        System.out.println("coordenadas del segundo punto:");
        System.out.print("x: ");
        x2 = lector.nextDouble();
        System.out.print("y: ");
        y2 = lector.nextDouble();

        // proceso

        double distancia = Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));
        double m = (y2 - y1) / (x2 - x1);
        double b = y1 - m * x1;
        String ecuacion = "y = " + m + " x + " + b;

        if (b < 0) {
            ecuacion += b; // ecuacion = ecuacion + b;
            
        } else if (b > 0) {
            ecuacion += " + " + b; // 
        }

        // mostrar los resultados 
        System.out.println("la distancia entre los puntos es " + distancia);
        System.out.println("la ecuacion de la recta que pasa por los puntos es " + ecuacion);


        // cerrar el lector de variables
        lector.close();
    }
}
