import javax.swing.JButton; // Crear botones
import javax.swing.JComboBox; // Crear lista desplegable
import javax.swing.JFrame; // Crear ventana
import javax.swing.JLabel; // Crear etiquetas
import javax.swing.JOptionPane; // Mostrar mensajes
import javax.swing.JScrollPane; // Agregar scroll a la tabla
import javax.swing.JTable; // Crear tabla
import javax.swing.JTextField; // Crear cajas de texto
import javax.swing.table.DefaultTableModel; // Modelo para la tabla

public class FrmDevuelta extends JFrame { // Clase principal

    private int[] denominaciones = {100000,50000,20000,10000,5000,2000,1000,500,200,100,50}; // Denominaciones disponibles
    private int[] existencias = new int[denominaciones.length]; // Existencia de cada denominación
    private String[] encabezados = {"Cantidad", "Presentación", "Denominación"}; // Encabezados de la tabla

    private JTextField txtExistencia; // Caja para existencia
    private JComboBox<Integer> cmbDenominacion; // Lista de denominaciones
    private JTextField txtDevuelta; // Caja para valor a devolver
    private JTable tblDevuelta; // Tabla de resultados

    public FrmDevuelta() { // Constructor

        setSize(400, 400); // Tamaño de ventana
        setTitle("Cálculo de Devuelta"); // Título
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar programa
        setLayout(null); // Posicionamiento manual

        JLabel lblDenominacion = new JLabel("Denominación"); // Etiqueta denominación
        lblDenominacion.setBounds(10, 10, 100, 25); // Posición
        add(lblDenominacion); // Agregar etiqueta

        cmbDenominacion = new JComboBox<Integer>(); // Crear lista

        for (int denominacion : denominaciones) { // Recorrer denominaciones
            cmbDenominacion.addItem(denominacion); // Agregar cada denominación
        }

        cmbDenominacion.setBounds(120, 10, 100, 25); // Posición de lista
        add(cmbDenominacion); // Agregar lista

        txtExistencia = new JTextField(); // Crear caja existencia
        txtExistencia.setBounds(230, 10, 100, 25); // Posición
        add(txtExistencia); // Agregar caja

        JButton btnExistencia = new JButton("Actualizar Existencia"); // Botón actualizar
        btnExistencia.setBounds(10, 45, 180, 25); // Posición
        add(btnExistencia); // Agregar botón

        JLabel lblValor = new JLabel("Valor a devolver"); // Etiqueta valor
        lblValor.setBounds(10, 80, 100, 25); // Posición
        add(lblValor); // Agregar etiqueta

        txtDevuelta = new JTextField(); // Crear caja valor
        txtDevuelta.setBounds(120, 80, 100, 25); // Posición
        add(txtDevuelta); // Agregar caja

        JButton btnDevuelta = new JButton("Devolver"); // Botón calcular devuelta
        btnDevuelta.setBounds(230, 80, 150, 25); // Posición
        add(btnDevuelta); // Agregar botón

        tblDevuelta = new JTable(); // Crear tabla
        JScrollPane spDevuelta = new JScrollPane(tblDevuelta); // Crear scroll para tabla
        spDevuelta.setBounds(10, 120, 370, 220); // Posición y tamaño
        add(spDevuelta); // Agregar tabla

        DefaultTableModel dtm = new DefaultTableModel(null, encabezados); // Modelo inicial vacío
        tblDevuelta.setModel(dtm); // Asignar modelo a tabla

        cmbDenominacion.addActionListener(evento -> { // Evento al cambiar denominación
            consultarExistencias(); // Mostrar existencia actual
        });

        btnExistencia.addActionListener(evento -> { // Evento botón actualizar
            actualizarExistencia(); // Guardar existencia
        });

        btnDevuelta.addActionListener(evento -> { // Evento botón devolver
            calcularDevuelta(); // Calcular devuelta
        });

        consultarExistencias(); // Mostrar existencia inicial
    }

    private void consultarExistencias() { // Consultar existencia seleccionada

        if (cmbDenominacion.getSelectedIndex() >= 0) { // Verificar selección
            int posicion = cmbDenominacion.getSelectedIndex(); // Obtener posición
            txtExistencia.setText(String.valueOf(existencias[posicion])); // Mostrar existencia
        }
    }

    private void actualizarExistencia() { // Actualizar existencia

        if (cmbDenominacion.getSelectedIndex() >= 0) { // Verificar selección

            try {
                int posicion = cmbDenominacion.getSelectedIndex(); // Obtener posición
                existencias[posicion] = Integer.parseInt(txtExistencia.getText()); // Guardar existencia

            } catch (Exception ex) {
                txtExistencia.setText(""); // Limpiar caja
                txtExistencia.requestFocus(); // Regresar cursor
                JOptionPane.showMessageDialog(null, "Debe ingresar un valor numérico para la existencia"); // Mostrar error
            }
        }
    }

    private void calcularDevuelta() { // Método para calcular devuelta

        try {
            int valorDevuelta = Integer.parseInt(txtDevuelta.getText()); // Leer valor a devolver
            int[] devuelta = new int[denominaciones.length]; // Guardar cantidad usada de cada denominación

            int posicionDenominacion = 0; // Posición actual
            int totalFilas = 0; // Cantidad de filas necesarias

            while (valorDevuelta > 0 && posicionDenominacion < denominaciones.length) { // Recorrer denominaciones

                if (valorDevuelta >= denominaciones[posicionDenominacion]) { // Verificar si cabe la denominación

                    int cantidadNecesaria = valorDevuelta / denominaciones[posicionDenominacion]; // Calcular cantidad

                    if (cantidadNecesaria > existencias[posicionDenominacion]) { // Comparar con existencia
                        cantidadNecesaria = existencias[posicionDenominacion]; // Usar solo las disponibles
                    }

                    if (cantidadNecesaria > 0) { // Verificar que se pueda usar alguna
                        valorDevuelta -= cantidadNecesaria * denominaciones[posicionDenominacion]; // Restar valor entregado
                        devuelta[posicionDenominacion] = cantidadNecesaria; // Guardar cantidad utilizada
                        totalFilas++; // Aumentar número de filas
                    }
                }

                posicionDenominacion++; // Pasar a siguiente denominación
            }

            if (totalFilas > 0) { // Verificar si se pudo devolver algo

                String[][] datos = new String[totalFilas][encabezados.length]; // Crear matriz para tabla

                totalFilas = 0; // Reiniciar contador para usarlo como fila

                for (int i = 0; i < denominaciones.length; i++) { // Recorrer denominaciones

                    if (devuelta[i] > 0) { // Mostrar solo las utilizadas

                        datos[totalFilas][0] = String.valueOf(devuelta[i]); // Columna cantidad

                        datos[totalFilas][1] = denominaciones[i] > 1000 ? "Billete(s)" : "Moneda(s)"; // Presentación

                        datos[totalFilas][2] = String.valueOf(denominaciones[i]); // Columna denominación

                        totalFilas++; // Pasar a siguiente fila
                    }
                }

                DefaultTableModel dtm = new DefaultTableModel(datos, encabezados); // Crear modelo con resultados
                tblDevuelta.setModel(dtm); // Mostrar resultados

                if (valorDevuelta > 0) { // Si quedó dinero pendiente
                    JOptionPane.showMessageDialog(null, "Está pendiente por devolver " + valorDevuelta); // Mostrar faltante
                }

            } else { // Si no fue posible devolver nada

                DefaultTableModel dtm = new DefaultTableModel(null, encabezados); // Tabla vacía
                tblDevuelta.setModel(dtm); // Limpiar tabla

                JOptionPane.showMessageDialog(null, "No hay forma de devolver"); // Mostrar mensaje
            }

        } catch (Exception ex) { // Si se escribe un valor incorrecto

            txtDevuelta.setText(""); // Limpiar caja
            txtDevuelta.requestFocus(); // Regresar cursor
            JOptionPane.showMessageDialog(null, "El valor a devolver debe ser un número entero"); // Mostrar error
        }
    }

    public static void main(String[] args) { // Método principal

        FrmDevuelta ventana = new FrmDevuelta(); // Crear ventana
        ventana.setVisible(true); // Mostrar ventana
    }
}

