// Importar las clases necesarias de Swing y AWT
import javax.swing.*;
import java.awt.*;

// Definición de la clase Juego que extiende de JFrame
public class Juego extends JFrame {

    // Constructor de la clase Juego
    public Juego() {
        setTitle("Adivina el número entre el 0 y el 100");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        // Crear una instancia de JuegoVista y JuegoControlador
        JuegoVista vista = new JuegoVista();
        JuegoControlador controlador = new JuegoControlador(vista);

        add(vista.getPanelPrincipal(), BorderLayout.CENTER);
    }

    // Método principal que crea una instancia de Juego y la hace visible
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Juego().setVisible(true);
            }
        });
    }
}
