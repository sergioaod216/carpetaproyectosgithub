import javax.swing.JButton; // Permite crear botones
import javax.swing.JFrame; // Permite crear ventanas
import javax.swing.JLabel; // Permite crear etiquetas
import javax.swing.JOptionPane; // Permite mostrar mensajes
import javax.swing.JTextField; // Permite crear cajas de texto

public class FrmConversion extends JFrame { // Clase principal que hereda de JFrame

    private JTextField txtarabigo; // Caja para escribir el número arábigo
    private JTextField txtromano; // Caja para mostrar el número romano

    public FrmConversion() { // Constructor de la ventana

        setSize(300, 200); // Define el tamaño de la ventana
        setTitle("Conversión a números romanos"); // Define el título
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra el programa al cerrar la ventana
        setLayout(null); // Permite ubicar elementos manualmente

        JLabel lblarabigo = new JLabel("Número arábigo"); // Crea una etiqueta
        lblarabigo.setBounds(10, 10, 120, 25); // Posición y tamaño de la etiqueta
        add(lblarabigo); // Agrega la etiqueta a la ventana

        txtarabigo = new JTextField(); // Crea la caja de entrada
        txtarabigo.setBounds(170, 10, 100, 25); // Posición y tamaño de la caja
        add(txtarabigo); // Agrega la caja a la ventana

        JButton btnconvertir = new JButton("Convertir a romano"); // Crea el botón
        btnconvertir.setBounds(10, 45, 150, 25); // Posición y tamaño del botón
        add(btnconvertir); // Agrega el botón a la ventana

        txtromano = new JTextField(); // Crea la caja del resultado
        txtromano.setBounds(170, 45, 100, 25); // Posición y tamaño
        txtromano.setEditable(false); // Impide que el usuario escriba allí
        add(txtromano); // Agrega la caja a la ventana

        btnconvertir.addActionListener(e -> { // Detecta cuando se presiona el botón
            convertiraromano(); // Llama al método de conversión
        });
    }

    private void convertiraromano() { // Método que convierte a número romano

        JOptionPane.showMessageDialog(null, "Conversión a números romanos"); // Muestra un mensaje

        String[] romanos = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"}; // Símbolos romanos
        int[] arabigos = {1000,900,500,400,100,90,50,40,10,9,5,4,1}; // Valores equivalentes

        try { // Intenta ejecutar el código

            int valorconvertir = Integer.parseInt(txtarabigo.getText()); // Convierte el texto a número entero
            System.out.println("Usted digitó: " + valorconvertir); // Muestra el valor en consola

            if (valorconvertir > 0 && valorconvertir < 4000) { // Valida que esté entre 1 y 3999

                int posiciondigito = 0; // Posición actual dentro de los arreglos
                String romano = ""; // Variable donde se construye el número romano

                while (valorconvertir > 0) { // Repite mientras quede valor por convertir

                    while (valorconvertir < arabigos[posiciondigito]) { // Busca un valor que pueda usarse
                        posiciondigito++; // Avanza a la siguiente posición
                    }

                    int digitosnecesarios = valorconvertir / arabigos[posiciondigito]; // Calcula cuántas veces cabe

                    for (int i = 0; i < digitosnecesarios; i++) { // Repite las veces necesarias
                        romano += romanos[posiciondigito]; // Agrega el símbolo romano
                    }

                    valorconvertir -= digitosnecesarios * arabigos[posiciondigito]; // Resta lo ya convertido
                }

                txtromano.setText(romano); // Muestra el resultado

            } else { // Se ejecuta si está fuera del rango
                JOptionPane.showMessageDialog(null, "Digite un número entre 1 y 3999"); // Mensaje de error
            }

        } catch (Exception ex) { // Captura errores como letras o campos vacíos
            JOptionPane.showMessageDialog(null, "Digite un valor numérico entero"); // Mensaje de error
        }
    }
}

